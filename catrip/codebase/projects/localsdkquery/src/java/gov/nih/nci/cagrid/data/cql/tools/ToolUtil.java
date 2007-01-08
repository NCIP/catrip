package gov.nih.nci.cagrid.data.cql.tools;

import java.lang.reflect.Field;

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
}
