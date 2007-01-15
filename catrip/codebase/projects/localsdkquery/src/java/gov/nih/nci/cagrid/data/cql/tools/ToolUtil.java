package gov.nih.nci.cagrid.data.cql.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ToolUtil {
    public ToolUtil() {
    }
    
    public static Class getClassToCheck(String property , Class cls) {
        boolean found = checkFiled(property,cls); 
        
        if(!found){
            Class superClass  = cls.getSuperclass();
            while (superClass != null) {
                found = checkFiled(property,superClass);  
                
                if (found) {
                    cls = superClass;
                    break;
                } else {
                    superClass = superClass.getSuperclass();
                }
            }
        }  
        
        return cls;
    }
    
    public static boolean checkFiled(String property, Class objectType){
        Field[] declaredFields = objectType.getDeclaredFields();
        Field field = null;
        boolean found = false;
        for (int i=0; i<declaredFields.length; i++) {
            field = (Field)declaredFields[i];
            if (property.equals(field.getName())) {
                found = true;
                break;
            }
            
        }
        return found;
    }
    
    public static Object performGetOperation(Class domainClass,Object domainObject, String memberName) throws Exception {
        String methodName = "get"+memberName.substring(0,1).toUpperCase()+memberName.substring(1,memberName.length());
        Method mthd = domainClass.getMethod(methodName,new Class[0]);
        Object obtainedObj = mthd.invoke(domainObject,new Object[0]);
        return obtainedObj;
    }
    
    public static List copyObjects(List resultObjectList,String className ) throws Exception {
        List newList = new ArrayList();
        
        Class cls = Class.forName(className);
        Constructor ct = cls.getConstructor(new Class[0]);
        
        
        for (int i=0;i<resultObjectList.size();i++) {
            Object row = resultObjectList.get(i); 
            newList.add(row);
        }
        return newList;
    }
    
    public static List buildObjcets(List resultObjectList,String[] returnAttrbs, String className ) throws Exception {
        List newList = new ArrayList();
        
        Class cls = Class.forName(className);
        Constructor ct = cls.getConstructor(new Class[0]);
        
        
        for (int i=0;i<resultObjectList.size();i++) {
            if (returnAttrbs == null ) {
                Object obj = ct.newInstance(new Object[0]);
                Object row = resultObjectList.get(i); 
                obj = performSetOperation(cls,obj,"id",row.getClass(),row);
                newList.add(obj);
            
            } else {
                Object[] row = (Object[])resultObjectList.get(i); 
                Object obj = ct.newInstance(new Object[0]);
                obj = performSetOperation(cls,obj,"id",row[0].getClass(),row[0]);                
                for (int k=0;k<row.length-1;k++) {
                    String memberName = returnAttrbs[k];
                   // System.out.println(memberName);
                   // System.out.println(row[k+1]);
                    if (row[k+1] != null ){
                        obj = performSetOperation(cls,obj,memberName,row[k+1].getClass(),row[k+1]);
                    }
                }      
                newList.add(obj);
            }
            //System.out.println(row.getClass().getName());
            //for (int k=0;k<row.length;k++) {
             //   System.out.println(row[k]);
            //}
        }
        
        return newList;
    }
    
    public static Object performSetOperation(Class cls,Object obj, String memberName, Class paramClass, Object paramObject) throws Exception{

        String methodName = "set"+memberName.substring(0,1).toUpperCase()+memberName.substring(1,memberName.length());
        Class[] p1 =new Class[1];
        p1[0] = paramClass;
        
        Object[] p2 = new Object[1];
        p2[0] = paramObject;  
            
        Method m1 = cls.getMethod(methodName,p1);
        m1.invoke(obj,p2);
        
        return obj;
    }
    public static void performSetOperation(String memberName,Class classToOperateOn, Object objectToOperateOn, List resultObjectList) throws Exception {
    
         String methodName = "set"+memberName.substring(0,1).toUpperCase()+memberName.substring(1,memberName.length());

         Class classTemp= ToolUtil.getClassToCheck(memberName,classToOperateOn);
         Field f = classTemp.getDeclaredField(memberName);
         Class dataType = f.getType();     
         
        Class[] p1 =new Class[1];
        p1[0] = dataType;
        
        if (memberName.endsWith("Collection")){

            Set hs = null;
            List al = null;
            if (dataType.getName().equals("java.util.Collection")) {
                // implementation can be HashSet,ArrayList
                 hs = new HashSet(resultObjectList);
                 al = new ArrayList(resultObjectList);
                
            } else if (dataType.getName().equals("java.util.List") || dataType.getName().equals("java.util.ArrayList")) {
                // implementation can be ArrayList
                 al = new ArrayList(resultObjectList);
            
            } else if (dataType.getName().equals("java.util.Set") || dataType.getName().equals("java.util.HashSet")) {
                // implementation can be HashSet  
                hs = new HashSet(resultObjectList);
            }

            
            Object[] p2 = new Object[1];
            p2[0] = resultObjectList.get(0);                   
            
            Method m1 = classToOperateOn.getMethod(methodName,p1);
            
            try {
                m1.invoke(objectToOperateOn,p2);
            } catch (Exception e1){
                p2[0] = hs; 
                m1.invoke(objectToOperateOn,p2);
                //e1.printStackTrace();
            }
            
            
        } else {
            Object[] p2 = new Object[1];
            p2[0] = resultObjectList.get(0);                   
            
            Method m1 = classToOperateOn.getMethod(methodName,p1);
            m1.invoke(objectToOperateOn,p2);
            
        }
    }
}