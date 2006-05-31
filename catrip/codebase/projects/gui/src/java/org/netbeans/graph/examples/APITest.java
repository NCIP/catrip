/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.graph.examples;

import org.netbeans.graph.api.GraphFactory;
import org.netbeans.graph.api.IGraphEventHandler;
import org.netbeans.graph.api.control.*;
import org.netbeans.graph.api.control.builtin.AnimatedNodesLayouter;
import org.netbeans.graph.api.control.builtin.BreadthFirstNodesLayouter;
import org.netbeans.graph.api.control.builtin.DefaultViewController;
import org.netbeans.graph.api.control.editor.IGraphEditor;
import org.netbeans.graph.api.control.editor.TextFieldEditor;
import org.netbeans.graph.api.model.*;
import org.netbeans.graph.api.model.ability.IDirectionable;
import org.netbeans.graph.api.model.ability.IDisplayable;
import org.netbeans.graph.api.model.ability.INameEditable;
import org.netbeans.graph.api.model.builtin.GraphDocument;
import org.netbeans.graph.api.model.builtin.GraphLink;
import org.netbeans.graph.api.model.builtin.GraphNode;
import org.netbeans.graph.api.model.builtin.GraphPort;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author David Kaspar
 */
public class APITest {

    public static void main (String[] args) {
        HashSet messages = new HashSet ();
        Class[] classes = new Class[] {
            GraphFactory.class, IGraphEventHandler.class,
            GraphEvent.class, IGraphDocument.class, IGraphLink.class, IGraphNode.class, IGraphPort.class,
            IDirectionable.class, IDisplayable.class, INameEditable.class,
            GraphDocument.class, GraphLink.class, GraphNode.class, GraphPort.class,
            GraphHelper.class, IGraphDocumentRenderer.class, IGraphLinkRenderer.class, IGraphLinkRouter.class, IGraphNodeRenderer.class, IGraphNodesLayouter.class, IGraphPortRenderer.class, IGraphViewController.class,
            AnimatedNodesLayouter.class, BreadthFirstNodesLayouter.class, DefaultViewController.class,
            IGraphEditor.class, TextFieldEditor.class,
        };
        for (int i = 0; i < classes.length; i++)
            updateResult (messages, classes[i]);
        String[] sortedMessages = (String[]) messages.toArray (new String[0]);
        Arrays.sort (sortedMessages);
        for (int i = 0; i < sortedMessages.length; i++)
            System.out.println (sortedMessages[i]);
    }

    private static void updateResult (HashSet messages, Class clazz) {
        if (isVisible (clazz.getModifiers ())) {
            boolean isNotFinal = ! clazz.isInterface ()  &&  ! isFinal (clazz.getModifiers ());
            addUsedClasses (messages, clazz.getInterfaces (), clazz);
            final Class superclass = clazz.getSuperclass ();
            if (! Object.class.equals (superclass))
                addUsedClass (messages, superclass, clazz);
            Constructor[] constructors = clazz.getDeclaredConstructors ();
            for (int i = 0; i < constructors.length; i++) {
                Constructor constructor = constructors[i];
                if (! isVisible (constructor.getModifiers ()))
                    continue;
                if (isNotFinal  &&  ! isFinal (constructor.getModifiers ()))
                    addUsedNotFinal (messages, constructor);
                addUsedClasses (messages, constructor.getExceptionTypes (), clazz);
                addUsedClasses (messages, constructor.getParameterTypes (), clazz);
            }
            Method[] methods = clazz.getDeclaredMethods ();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (!isVisible (method.getModifiers ()))
                    continue;
                if (isNotFinal  &&  ! isFinal (method.getModifiers ()))
                    addUsedNotFinal (messages, method);
                addUsedClass (messages, method.getReturnType (), clazz);
                addUsedClasses (messages, method.getExceptionTypes (), clazz);
                addUsedClasses (messages, method.getParameterTypes (), clazz);
            }
            Field[] fields = clazz.getDeclaredFields ();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (!isVisible (field.getModifiers ()))
                    continue;
                if (isNotFinal  &&  ! isFinal (field.getModifiers ()))
                    addUsedNotFinal (messages, field);
                addUsedClass (messages, field.getType (), clazz);
                addUsedField (messages, field);
            }
        }
        Class[] classes = clazz.getDeclaredClasses ();
        for (int i = 0; i < classes.length; i++)
            updateResult (messages, classes[i]);
    }

    private static void addUsedNotFinal (HashSet messages, Constructor constructor) {
        if (constructor != null)
            messages.add ("Used Not Final: " + constructor.getDeclaringClass ().getName () + " " + constructor.getName ());
    }

    private static void addUsedNotFinal (HashSet messages, Method method) {
        if (method != null)
            messages.add ("Used Not Final: " + method.getDeclaringClass ().getName () + " " + method.getName ());
    }

    private static void addUsedNotFinal (HashSet messages, Field field) {
        if (field != null)
            messages.add ("Used Not Final: " + field.getDeclaringClass ().getName () + " " + field.getName ());
    }

    private static boolean isFinal (int modifiers) {
        return Modifier.isFinal (modifiers)  ||  Modifier.isAbstract (modifiers);
    }

    private static boolean isVisible (int modifiers) {
        return Modifier.isPublic (modifiers)  ||  Modifier.isProtected (modifiers);
    }

    private static void addUsedClasses (HashSet messages, Class[] classes, Class where) {
        if (classes != null)
            for (int i = 0; i < classes.length; i++)
                addUsedClass (messages, classes[i], where);
    }

    private static void addUsedClass (HashSet messages, Class clazz, Class where) {
        if (clazz != null) {
            messages.add ("Used Class: " + clazz.getName ());
            System.out.println ("Class: " + clazz.getName () + "  Where: " + where.getName ());
        }
    }

    private static void addUsedField (HashSet messages, Field field) {
        if (field != null)
            messages.add ("Used Field: " + field.getDeclaringClass ().getName () + " " + field.getName ());
    }

}
