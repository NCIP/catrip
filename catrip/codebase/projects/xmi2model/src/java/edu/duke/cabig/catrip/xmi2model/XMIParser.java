/*
 * Created on Oct 18, 2006
 */
package edu.duke.cabig.catrip.xmi2model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.metadata.common.SemanticMetadata;
import gov.nih.nci.cagrid.metadata.common.UMLAttribute;
import gov.nih.nci.cagrid.metadata.common.UMLClass;
import gov.nih.nci.cagrid.metadata.common.UMLClassUmlAttributeCollection;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModelExposedUMLAssociationCollection;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModelExposedUMLClassCollection;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModelUmlGeneralizationCollection;
import gov.nih.nci.cagrid.metadata.dataservice.UMLAssociation;
import gov.nih.nci.cagrid.metadata.dataservice.UMLAssociationEdge;
import gov.nih.nci.cagrid.metadata.dataservice.UMLAssociationSourceUMLAssociationEdge;
import gov.nih.nci.cagrid.metadata.dataservice.UMLAssociationTargetUMLAssociationEdge;
import gov.nih.nci.cagrid.metadata.dataservice.UMLClassReference;
import gov.nih.nci.cagrid.metadata.dataservice.UMLGeneralization;

public class XMIParser
{
	private DomainModel model;
	private boolean filterPrimitiveClasses = true;
	private String projectDescription;
	private String projectLongName;
	private String projectShortName;
	private String projectVersion;
	private float attributeVersion = 1.0f;
	private boolean debug = false;
	
	private static final Hashtable<String,String> DATATYPE_MAP = new Hashtable<String,String>();
	{
		DATATYPE_MAP.put("Date", "java.util.Date");
		DATATYPE_MAP.put("Short", "java.lang.Short");
		DATATYPE_MAP.put("Integer", "java.lang.Integer");
		DATATYPE_MAP.put("Long", "java.lang.Long");
		DATATYPE_MAP.put("Float", "java.lang.Float");
		DATATYPE_MAP.put("Double", "java.lang.Double");
		DATATYPE_MAP.put("Boolean", "java.lang.Boolean");
		DATATYPE_MAP.put("Byte", "java.lang.Byte");
		DATATYPE_MAP.put("String", "java.lang.String");
		DATATYPE_MAP.put("Character", "java.lang.Character");
	}

	public XMIParser(String projectShortName, String projectVersion)
	{
		super();
		
		this.projectShortName = projectShortName;
		this.projectVersion = projectVersion;
	}
	
	public DomainModel parse(File file) 
		throws SAXException, IOException, ParserConfigurationException
	{
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		XMIHandler handler = new XMIHandler();
		parser.parse(file, handler);
		return model;
	}
	
	public static void writeDomainModel(DomainModel model, File outFile) 
		throws Exception
	{
		Utils.serializeDocument(
			outFile.toString(), model, 
			new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel", "ns1")
		);
	}
	
	private class XMIHandler
		extends DefaultHandler
	{
		private StringBuffer chars = new StringBuffer();
		
		private ArrayList<UMLClass> clList = new ArrayList<UMLClass>();
		private ArrayList<UMLAttribute> attList = new ArrayList<UMLAttribute>();
		private ArrayList<UMLAssociation> assList = new ArrayList<UMLAssociation>();
		private ArrayList<UMLGeneralization> genList = new ArrayList<UMLGeneralization>();

		private Hashtable<String,UMLClass> clTable = new Hashtable<String,UMLClass>();
		private Hashtable<String,UMLAttribute> attTable = new Hashtable<String,UMLAttribute>();
		private Hashtable<String,ArrayList<SemanticMetadata>> smTable = new Hashtable<String,ArrayList<SemanticMetadata>>();
		private Hashtable<String,String> typeTable = new Hashtable<String,String>();

		private UMLAssociationEdge edge;
		private boolean sourceNavigable = false;
		private boolean targetNavigable = false;
		private String pkg = "";
		
		public XMIHandler()
		{
			super();
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException
		{
			chars.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException
		{
			if (qName.equals("UML:Package")) {
				int index = pkg.lastIndexOf('.');
				if (index == -1) pkg = "";
				else pkg = pkg.substring(0, index);
			} else if (qName.equals("UML:Class")) {
				UMLClass cl = clList.get(clList.size()-1);
				cl.setUmlAttributeCollection(new UMLClassUmlAttributeCollection(attList.toArray(new UMLAttribute[0])));
				attList.clear();
			} else if (qName.equals("UML:Association")) {
				UMLAssociation ass = assList.get(assList.size()-1);
				if (sourceNavigable && ! targetNavigable) {
					UMLAssociationEdge edge = ass.getSourceUMLAssociationEdge().getUMLAssociationEdge();
					ass.getSourceUMLAssociationEdge().setUMLAssociationEdge(ass.getTargetUMLAssociationEdge().getUMLAssociationEdge());
					ass.getTargetUMLAssociationEdge().setUMLAssociationEdge(edge);
				}
				ass.setBidirectional(sourceNavigable && targetNavigable);
			}
			
			chars.delete(0, chars.length());
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
		{
			chars.delete(0, chars.length());
			
			if (qName.equals("UML:Package")) {
				String name = atts.getValue("name");
				if (! name.equals("Logical View") && ! name.equals("Logical Model")) {
					if (! pkg.equals("")) pkg += ".";
					pkg += atts.getValue("name");
				}
			} else if (qName.equals("UML:Class")) {
				UMLClass cl = new UMLClass();
				cl.setClassName(atts.getValue("name"));
				cl.setId(atts.getValue("xmi.id"));
				cl.setPackageName(pkg);
				cl.setProjectName(projectShortName);
				cl.setProjectVersion(projectVersion);
				clList.add(cl);
				clTable.put(cl.getId(), cl);
			} else if (qName.equals("UML:Attribute")) {
				UMLAttribute att = new UMLAttribute();
				att.setName(atts.getValue("name"));
				att.setPublicID(atts.getValue("xmi.id").hashCode());
				att.setVersion(attributeVersion);
				attList.add(att);
				attTable.put(String.valueOf(att.getPublicID()), att);
			} else if (qName.equals("UML:Association")) {
				UMLAssociation ass = new UMLAssociation();
				assList.add(ass);
			} else if (qName.equals("UML:AssociationEnd")) {
				UMLAssociation ass = assList.get(assList.size()-1);
				String type = atts.getValue("type");
				boolean isNavigable = "true".equals(atts.getValue("isNavigable"));
				
				edge = new UMLAssociationEdge();
				if (ass.getSourceUMLAssociationEdge() == null) {
					ass.setSourceUMLAssociationEdge(new UMLAssociationSourceUMLAssociationEdge(edge));
					sourceNavigable = isNavigable;
				} else {
					ass.setTargetUMLAssociationEdge(new UMLAssociationTargetUMLAssociationEdge(edge));
					targetNavigable = isNavigable;
				}
				edge.setRoleName(atts.getValue("name"));
				edge.setUMLClassReference(new UMLClassReference(atts.getValue("type")));
			} else if (qName.equals("UML:MultiplicityRange") && edge != null) {
				edge.setMinCardinality(Integer.parseInt(atts.getValue("lower")));
				edge.setMaxCardinality(Integer.parseInt(atts.getValue("upper")));
			} else if (qName.equals("UML:Generalization")) {
				UMLGeneralization gen = new UMLGeneralization();
				gen.setSubClassReference(new UMLClassReference(atts.getValue("child")));
				gen.setSuperClassReference(new UMLClassReference(atts.getValue("parent")));
				genList.add(gen);
			} else if (qName.equals("UML:TaggedValue")) {
				String tag = atts.getValue("tag");
				String modelElement = atts.getValue("modelElement");
				String value = atts.getValue("value");
				
				if (debug) System.out.print(tag + " on " + modelElement);
				if (tag.startsWith("Property")) {
					modelElement = String.valueOf(modelElement.hashCode());
					if (debug) System.out.print(" (" + modelElement + ")");
				}
				if (debug) System.out.println(" = " + value);
				
				if (tag.equals("description")) {
					if (clTable.containsKey(modelElement)) {
						clTable.get(modelElement).setDescription(value);
					} else if (attTable.containsKey(modelElement)) {
						attTable.get(modelElement).setDescription(value);
					}
				} else if (tag.startsWith("ObjectClassConceptCode") || tag.startsWith("ObjectClassQualifierConceptCode") 
					|| tag.startsWith("PropertyConceptCode") || tag.startsWith("PropertyQualifierConceptCode")
				) {
					addSemanticMetadata(tag, modelElement, value);
				} else if (tag.startsWith("ObjectClassConceptPreferredName") || tag.startsWith("ObjectClassQualifierConceptPreferredName")
					|| tag.startsWith("PropertyConceptPreferredName") || tag.startsWith("PropertyQualifierConceptPreferredName")
				) {
					addSemanticMetadata(tag, modelElement, value);
				} else if ((tag.startsWith("ObjectClassConceptDefinition") && ! tag.startsWith("ObjectClassConceptDefinitionSource"))
					|| (tag.startsWith("ObjectClassQualifierConceptDefinition") && ! tag.startsWith("ObjectClassQualifierConceptDefinitionSource"))
					|| (tag.startsWith("PropertyConceptDefinition") && ! tag.startsWith("PropertyConceptDefinitionSource"))
					|| (tag.startsWith("PropertyQualifierConceptDefinition") && ! tag.startsWith("PropertyQualifierConceptDefinitionSource"))
				) {
					addSemanticMetadata(tag, modelElement, value);
				}
			} else if (qName.equals("UML:DataType")) {
				typeTable.put(atts.getValue("xmi.id"), atts.getValue("name"));
			} else if (qName.equals("Foundation.Core.Classifier")) {
				attList.get(attList.size()-1).setDataTypeName(atts.getValue("xmi.idref"));
			}
		}

		public void endDocument() throws SAXException
		{
			applySemanticMetadata();
			applyDataTypes();
			flattenAttributes();
			applyFilters();
			
			model = new DomainModel();
			
			model.setProjectShortName(projectShortName);
			model.setProjectLongName(projectLongName);
			model.setProjectVersion(projectVersion);
			model.setProjectDescription(projectDescription);
			
			model.setExposedUMLClassCollection(new DomainModelExposedUMLClassCollection(clList.toArray(new UMLClass[0])));
			model.setExposedUMLAssociationCollection(new DomainModelExposedUMLAssociationCollection(assList.toArray(new UMLAssociation[0])));
			model.setUmlGeneralizationCollection(new DomainModelUmlGeneralizationCollection(genList.toArray(new UMLGeneralization[0])));
		}
		
		private int getSemanticMetadataOrder(String tag)
		{
			char c = tag.charAt(tag.length()-1);
			if (Character.isDigit(c)) return Integer.parseInt(String.valueOf(c));
			return 0;
		}

		private void addSemanticMetadata(String tag, String modelElement, String value)
		{
			int order = getSemanticMetadataOrder(tag);

			ArrayList<SemanticMetadata> smList = smTable.get(modelElement);
			if (smList == null) smTable.put(modelElement, smList = new ArrayList<SemanticMetadata>(9));
			
			int size = smList.size();
			if (size <= order) {
				for (int i = smList.size(); i <= order; i++) {
					smList.add(new SemanticMetadata());
				}
			}
			
			SemanticMetadata sm = smList.get(order);			
			if (tag.indexOf("ConceptCode") != -1) {
				sm.setOrder(order);
				sm.setConceptCode(value);
			} else if (tag.indexOf("PreferredName") != -1) {
				sm.setConceptName(value);
			} else if (tag.indexOf("ConceptDefinition") != -1) {
				sm.setConceptDefinition(value);
			}
		}
		
		private void applySemanticMetadata()
		{
			for (String id : smTable.keySet()) {
				if (clTable.containsKey(id)) {
					clTable.get(id).setSemanticMetadata(smTable.get(id).toArray(new SemanticMetadata[0]));
				} else if (attTable.containsKey(id)) {
					attTable.get(id).setSemanticMetadata(smTable.get(id).toArray(new SemanticMetadata[0]));
				}
			}
		}
		
		private void applyDataTypes()
		{
			for (String id : attTable.keySet()) {
				UMLAttribute att = attTable.get(id);
				String typeRef = att.getDataTypeName();

				String dataType = null;

				// check for class
				if (dataType == null) {
					UMLClass typeCl = clTable.get(typeRef);
					if (typeCl != null) dataType = typeCl.getClassName();
				}
				
				// check type table
				if (dataType == null) {
					dataType = typeTable.get(typeRef);
				}
				
				// perform mapping
				if (dataType != null && DATATYPE_MAP.containsKey(dataType)) {
					dataType = DATATYPE_MAP.get(dataType);
				}
				
				// set data type
				att.setDataTypeName(dataType);
			}
		}
		
		private void flattenAttributes()
		{
			// build parent table
			Hashtable<String,String> parentTable = new Hashtable<String,String>();
			for (UMLGeneralization gen : genList) {
				parentTable.put(gen.getSubClassReference().getRefid(), gen.getSuperClassReference().getRefid());
			}
			
			// flatten each cl
			for (String clId : clTable.keySet()) {
				UMLClass cl = clTable.get(clId);
				ArrayList<UMLAttribute> attList = flattenAttributes(parentTable, clId);
				cl.getUmlAttributeCollection().setUMLAttribute(attList.toArray(new UMLAttribute[0]));
			}
		}
		
		private ArrayList<UMLAttribute> flattenAttributes(Hashtable<String,String> parentTable, String clId)
		{
			if (clId == null) return new ArrayList<UMLAttribute>(0);
			ArrayList<UMLAttribute> attList = new ArrayList<UMLAttribute>();
			
			// my atts
			UMLClass cl = clTable.get(clId);
			for (UMLAttribute att : cl.getUmlAttributeCollection().getUMLAttribute()) {
				attList.add(att);
			}
			// my parent's atts
			for (UMLAttribute att : flattenAttributes(parentTable, parentTable.get(clId))) {
				attList.add(att);
			}
			
			return attList;
		}
		
		private void applyFilters()
		{
			// build filter set
			HashSet<String> filterSet = new HashSet<String>();
			// filter primtives
			if (filterPrimitiveClasses) {
				for (UMLClass cl : clList) {
					if (cl.getPackageName().startsWith("java")) filterSet.add(cl.getId());
				}
			}
			// filter root class
			for (UMLClass cl : clList) {
				if (cl.getPackageName().equals("")) filterSet.add(cl.getId());
			}
			
			// filter classes
			ArrayList<UMLClass> clList = new ArrayList<UMLClass>(this.clList.size());
			for (UMLClass cl : this.clList) {
				if (! filterSet.contains(cl.getId())) clList.add(cl);
			}
			this.clList = clList;
			
			// filter assocations
			ArrayList<UMLAssociation> assList = new ArrayList<UMLAssociation>(this.assList.size());
			for (UMLAssociation ass : this.assList) {
				if (! filterSet.contains(ass.getSourceUMLAssociationEdge().getUMLAssociationEdge().getUMLClassReference().getRefid())
					&& ! filterSet.contains(ass.getTargetUMLAssociationEdge().getUMLAssociationEdge().getUMLClassReference().getRefid())
				) {
					assList.add(ass);
				}
			}
			this.assList = assList;
			
			// filter generalizations
			ArrayList<UMLGeneralization> genList = new ArrayList<UMLGeneralization>(this.genList.size());
			for (UMLGeneralization gen : this.genList) {
				if (! filterSet.contains(gen.getSubClassReference().getRefid())
					&& ! filterSet.contains(gen.getSuperClassReference().getRefid())
				) {
					genList.add(gen);
				}
			}
			this.genList = genList;
		}
	}

	public boolean isFilterPrimitiveClasses()
	{
		return filterPrimitiveClasses;
	}

	public void setFilterPrimitiveClasses(boolean filterPrimitiveClasses)
	{
		this.filterPrimitiveClasses = filterPrimitiveClasses;
	}

	public String getProjectDescription()
	{
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription)
	{
		this.projectDescription = projectDescription;
	}

	public String getProjectLongName()
	{
		return projectLongName;
	}

	public void setProjectLongName(String projectLongName)
	{
		this.projectLongName = projectLongName;
	}

	public String getProjectShortName()
	{
		return projectShortName;
	}

	public void setProjectShortName(String projectShortName)
	{
		this.projectShortName = projectShortName;
	}

	public String getProjectVersion()
	{
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion)
	{
		this.projectVersion = projectVersion;
	}

	public float getAttributeVersion()
	{
		return attributeVersion;
	}

	public void setAttributeVersion(float attributeVersion)
	{
		this.attributeVersion = attributeVersion;
	}

	public boolean isDebug()
	{
		return debug;
	}

	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}
}
