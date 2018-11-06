package utils;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 自动创建某对象mock数据
 * @Author hzsunguanjun@corp.netease.com
 * @Date 2018/1/10
 */
public class MockUtil {

    /**
     *
     * @param clazz
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<T> genMockList(Class clazz, int size){
        List<T> result = new ArrayList<>();

        for (int i=0; i<size; i++){
            result.add(genMockInstance(clazz));
        }

        return result;
    }

    public static <T> T genMockInstance (Class clazz){
        try {
            T object = (T) clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods){
                if (method.getName().startsWith("set")){
                    // 因为是赋值方法，所以参数一定只有一个
                    Class[] classes = method.getParameterTypes();

                    // 枚举暂不支持
                    if (!classes[0].getCanonicalName().contains("enums")){
                        method.invoke(object, getRandomParam(classes[0], method));
                    }
                }

            }

            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getRandomParam (Class paramClass, Method method) throws Exception{
        switch (paramClass.getCanonicalName()){
            case "int":
            case "java.lang.Integer":
                return RandomUtils.nextInt(100);
            case "long":
            case "java.lang.Long":
                return RandomUtils.nextLong();
            case "float":
            case "java.lang.Float":
                return RandomUtils.nextFloat();
            case "double":
            case "java.lang.Double":
                return RandomUtils.nextDouble();
            case "java.lang.String":
                return RandomStringUtils.randomAlphabetic(6);
            case "java.util.List":
                Type[] genericTypes = getCollectionTypes(method);
                String typeName = genericTypes[0].getTypeName();
                Class ttt = Class.forName(typeName);

                List list = new ArrayList();
                // 嵌套集合暂不支持
                list.add(getRandomParam(ttt, null));
                return list;

            case "java.util.Map":
                genericTypes = getCollectionTypes(method);
                typeName = genericTypes[0].getTypeName();
                Class ttt0 = Class.forName(typeName);
                typeName = genericTypes[1].getTypeName();
                Class ttt1 = Class.forName(typeName);

                Map map = new HashMap<>();
                // 嵌套集合暂不支持
                map.put(getRandomParam(ttt0, null),
                        getRandomParam(ttt1, null));
                return map;
            case "java.util.Set":
                genericTypes = getCollectionTypes(method);
                typeName = genericTypes[0].getTypeName();
                ttt = Class.forName(typeName);

                Set set = new HashSet<>();
                // 嵌套集合暂不支持
                set.add(getRandomParam(ttt, null));
                return set;
            default:
                return genMockInstance(paramClass);
        }
    }


    public static Type[] getCollectionTypes(Method method){
        Type[] types = method.getGenericParameterTypes();
        ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) types[0]; //强转成具体的实现类
        Type[] genericTypes = parameterizedType.getActualTypeArguments();  //取得包含的泛型类型
        return genericTypes;
    }
}
