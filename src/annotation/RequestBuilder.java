package annotation;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.reflect.Field;
import java.util.*;

public class RequestBuilder <T extends Entity> implements EntityManager <T> {

    public void save(T object) {
        if(object!=null){
            Class<?> clazz =object.getClass();
            if(clazz.isAnnotationPresent(ObjProperties.class)){
                ObjProperties annotation=clazz.getAnnotation(ObjProperties.class);
                int objectTypeId=annotation.objType();
                Reflections reflections=new Reflections("annotation",new FieldAnnotationsScanner());
                Field[] fieldsID=new Field[reflections.getFieldsAnnotatedWith(Id.class).size()];
                reflections.getFieldsAnnotatedWith(Id.class).toArray(fieldsID);
                fieldsID[0].setAccessible(true);
                Long objectId = null;
                try {
                    objectId=(Long) fieldsID[0].get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(objectId==null){
                    System.out.println("Тут insert в БД в objects и получаем по sequence ID "+objectTypeId);
                    objectId=1L;
                }
                Set<Field> fieldsAttribute=getFieldsRecursion(new HashSet<Field>(),clazz);
                for (Field field : fieldsAttribute){
                    try {
                        if(field.isAnnotationPresent(Attribute.class)) {
                            field.setAccessible(true);
                            Attribute annotationAttribute=field.getAnnotation(Attribute.class);
                            int attributeId=annotationAttribute.attrId();
                            if(Date.class.isAssignableFrom(field.getType())){
                                System.out.println("Merge и данные "+attributeId+" "+objectId+" date="+  field.get(object));
                            }
                            else {
                                System.out.println("Merge и данные "+attributeId+" "+objectId+" value="+  field.get(object));
                            }

                            System.out.println();
                        }
                        if(field.isAnnotationPresent(AttList.class)){
                            System.out.println("Записываем по листу предварительно взяв номер");
                        }
                        if(field.isAnnotationPresent(AttReference.class)){
                            AttReference annotationAttribute=field.getAnnotation(AttReference.class);
                            int attributeId=annotationAttribute.attrId();
                            field.setAccessible(true);
                            if(field.get(object)!=null) {
                                if (Collection.class.isAssignableFrom(field.getType())) {
                                    Collection collection = (Collection) field.get(object);

                                    for (Object object1 : collection) {
                                        Long objectId1 = (Long) fieldsID[0].get(object1);
                                        System.out.println("Заносим в референсы коллекцию " + attributeId + " " + objectId + " " + objectId1);
                                    }
                                } else {
                                    Long objectId1 = (Long) fieldsID[0].get(field.get(object));
                                    System.out.println("Заносим в референсы " + attributeId + " " + objectId + " " + objectId1);
                                }
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Коммит");
            }
        }
    }


    public List<T> getEntityListBySql(String sqlQuery) {
        return null;
    }


    public List<T> getEntityListByPage(Integer from, Integer limit, Class clazz) {
        return null;
    }


    public T gerEntityById(Integer id, Class clazz) {
        return null;
    }


    public void delete(T object) {

    }
    private Set<Field> getFieldsRecursion(Set<Field> fields,Class<?> clazz){
        if(!clazz.equals(Object.class)){
            Field[] fields1=clazz.getDeclaredFields();
            Collections.addAll(fields, fields1);
            getFieldsRecursion(fields,clazz.getSuperclass());
        }
        return fields;
    }
}
