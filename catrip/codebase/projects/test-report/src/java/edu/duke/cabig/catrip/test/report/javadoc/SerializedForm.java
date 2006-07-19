/**
 * @(#)SerializedForm.java	1.24 03/12/19
 *
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package edu.duke.cabig.catrip.test.report.javadoc;

import com.sun.javadoc.*;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.code.Symbol.ClassSymbol;
import com.sun.tools.javac.code.Symbol.MethodSymbol;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.ListBuffer;

/**
 * The serialized form is the specification of a class' serialization
 * state. <p>
 *
 * It consists of the following information:<p>
 *
 * <pre>
 * 1. Whether class is Serializable or Externalizable.
 * 2. Javadoc for serialization methods.
 *    a. For Serializable, the optional readObject, writeObject,
 *       readResolve and writeReplace.
 *       serialData tag describes, in prose, the sequence and type
 *       of optional data written by writeObject.
 *    b. For Externalizable, writeExternal and readExternal.
 *       serialData tag describes, in prose, the sequence and type
 *       of optional data written by writeExternal.
 * 3. Javadoc for serialization data layout.
 *    a. For Serializable, the name,type and description
 *       of each Serializable fields.
 *    b. For Externalizable, data layout is described by 2(b).
 * </pre>
 *
 * @since JDK1.2
 * @author Joe Fialli
 * @author Neal Gafter (rewrite but not too proud)
 */
class SerializedForm {
    ListBuffer<MethodDoc> methods = new ListBuffer<MethodDoc>();

    /* List of FieldDocImpl - Serializable fields.
     * Singleton list if class defines Serializable fields explicitly.
     * Otherwise, list of default serializable fields.
     * 0 length list for Externalizable.
     */
    private final ListBuffer<FieldDocImpl> fields = new ListBuffer<FieldDocImpl>();

    /* True if class specifies serializable fields explicitly.
     * using special static member, serialPersistentFields.
     */
    private boolean definesSerializableFields = false;

    // Specially treated field/method names defined by Serialization.
    private static final String SERIALIZABLE_FIELDS = "serialPersistentFields";
    private static final String READOBJECT  = "readObject";
    private static final String WRITEOBJECT = "writeObject";
    private static final String READRESOLVE  = "readResolve";
    private static final String WRITEREPLACE = "writeReplace";
    private static final String READOBJECTNODATA = "readObjectNoData";

    /**
     * Constructor.
     *
     * Catalog Serializable fields for Serializable class.
     * Catalog serialization methods for Serializable and
     * Externalizable classes.
     */
    SerializedForm(DocEnv env, ClassSymbol def, ClassDocImpl cd) {
        if (cd.isExternalizable()) {
	    /* look up required public accessible methods,
	     *   writeExternal and readExternal.
	     */
            String[] readExternalParamArr = { "java.io.ObjectInput" };
            String[] writeExternalParamArr = { "java.io.ObjectOutput" };
	    MethodDoc md = cd.findMethod("readExternal", readExternalParamArr);
	    if (md != null) {
		methods.append(md);
            }
	    md = cd.findMethod("writeExternal", writeExternalParamArr);
	    if (md != null) {
		methods.append(md);
		Tag tag[] = md.tags("serialData");
	    }
	// } else { // isSerializable() //### ???
	} else if (cd.isSerializable()) {

	    VarSymbol dsf = getDefinedSerializableFields(def);
	    if (dsf != null) {

		/* Define serializable fields with array of ObjectStreamField.
		 * Each ObjectStreamField should be documented by a
		 * serialField tag.
		 */
		definesSerializableFields = true;
		//### No modifier filtering applied here.
		FieldDocImpl dsfDoc = env.getFieldDoc(dsf);
		fields.append(dsfDoc);
		mapSerialFieldTagImplsToFieldDocImpls(dsfDoc, env, def);
            } else {

		/* Calculate default Serializable fields as all
		 * non-transient, non-static fields.
		 * Fields should be documented by serial tag.
		 */
		computeDefaultSerializableFields(env, def, cd);
	    }

           /* Check for optional customized readObject, writeObject, 
            * readResolve and writeReplace, which can all contain 
            * the serialData tag.	 */
            addMethodIfExist(env, def, READOBJECT); 
            addMethodIfExist(env, def, WRITEOBJECT); 
            addMethodIfExist(env, def, READRESOLVE); 
            addMethodIfExist(env, def, WRITEREPLACE);
            addMethodIfExist(env, def, READOBJECTNODATA);
	}
    }

    /*
     * Check for explicit Serializable fields. 
     * Check for a private static array of ObjectStreamField with 
     * name SERIALIZABLE_FIELDS.
     */
    private VarSymbol getDefinedSerializableFields(ClassSymbol def) {
	Name.Table names = def.name.table;

	/* SERIALIZABLE_FIELDS can be private,
	 * so must lookup by ClassSymbol, not by ClassDocImpl.
	 */
	for (Scope.Entry e = def.members().lookup(names.fromString(SERIALIZABLE_FIELDS)); e.scope != null; e = e.next()) {
	    if (e.sym.kind == Kinds.VAR) {
		VarSymbol f = (VarSymbol)e.sym;
		if ((f.flags() & Flags.STATIC) != 0 &&
		    (f.flags() & Flags.PRIVATE) != 0) {
		    return f;
		}
	    }
	}
	return null;
    }

    /*
     * Compute default Serializable fields from all members of ClassSymbol.
     *
     * Since the fields of ClassDocImpl might not contain private or
     * package accessible fields, must walk over all members of ClassSymbol.
     */
    private void computeDefaultSerializableFields(DocEnv env,
						  ClassSymbol def,
						  ClassDocImpl cd) {
	for (Scope.Entry e = def.members().elems; e != null; e = e.sibling) {
	    if (e.sym != null && e.sym.kind == Kinds.VAR) {
		VarSymbol f = (VarSymbol)e.sym;
		if ((f.flags() & Flags.STATIC) == 0 &&
		    (f.flags() & Flags.TRANSIENT) == 0) {
		    //### No modifier filtering applied here.
		    FieldDocImpl fd = env.getFieldDoc(f);
		    //### Add to beginning.
		    //### Preserve order used by old 'javadoc'.
		    fields.prepend(fd);
		}
	    }
	}
    }

    /*
     * Catalog Serializable method if it exists in current ClassSymbol.
     * Do not look for method in superclasses.
     *
     * Serialization requires these methods to be non-static.
     *
     * @param method should be an unqualified Serializable method
     *               name either READOBJECT, WRITEOBJECT, READRESOLVE 
     *               or WRITEREPLACE.
     * @param visibility the visibility flag for the given method.
     */
    private void addMethodIfExist(DocEnv env, ClassSymbol def, String methodName) {
	Name.Table names = def.name.table;

	for (Scope.Entry e = def.members().lookup(names.fromString(methodName)); e.scope != null; e = e.next()) {
	    if (e.sym.kind == Kinds.MTH) {
		MethodSymbol md = (MethodSymbol)e.sym;
		if ((md.flags() & Flags.STATIC) == 0) {
		    /*
		     * WARNING: not robust if unqualifiedMethodName is overloaded
		     *          method. Signature checking could make more robust.
		     * READOBJECT takes a single parameter, java.io.ObjectInputStream.
		     * WRITEOBJECT takes a single parameter, java.io.ObjectOutputStream.
		     */
		    methods.append(env.getMethodDoc(md));
		}
	    }
	}
    }

    /*
     * Associate serialField tag fieldName with FieldDocImpl member.
     * Note: A serialField tag does not have to map an existing field
     *       of a class.
     */
    private void mapSerialFieldTagImplsToFieldDocImpls(FieldDocImpl spfDoc,
						       DocEnv env,
						       ClassSymbol def) {
	Name.Table names = def.name.table;

	SerialFieldTag[] sfTag = spfDoc.serialFieldTags();
	for (int i = 0; i < sfTag.length; i++) {
	    Name fieldName = names.fromString(sfTag[i].fieldName());

	    // Look for a FieldDocImpl that is documented by serialFieldTagImpl.
	    for (Scope.Entry e = def.members().lookup(fieldName); e.scope != null; e = e.next()) {
	        if (e.sym.kind == Kinds.VAR) {
	            VarSymbol f = (VarSymbol)e.sym;
		    FieldDocImpl fdi = env.getFieldDoc(f);
		    ((SerialFieldTagImpl)(sfTag[i])).mapToFieldDocImpl(fdi);
		    break;
		}
	    }
	}
    }

    /**
     * Return serializable fields in class. <p>
     *
     * Returns either a list of default fields documented by serial tag comment or
     *         javadoc comment<p>
     * Or Returns a single FieldDocImpl for serialPersistentField. There is a
     *         serialField tag for each serializable field.<p>
     *
     * @return an array of FieldDocImpl for representing the visible
     *         fields in this class.
     */
    FieldDoc[] fields() {
        return (FieldDoc[])fields.toArray(new FieldDocImpl[fields.length()]);
    }

    /**
     * Return serialization methods in class.
     *
     * @return an array of MethodDocImpl for serialization methods in this class.
     */
    MethodDoc[] methods() {
        return (MethodDoc[])methods.toArray(new MethodDoc[methods.length()]);
    }

    /**
     * Returns true if Serializable fields are defined explicitly using
     * member, serialPersistentFields.
     *
     * @see #fields()
     */
    boolean definesSerializableFields() {
	return definesSerializableFields;
    }
}


