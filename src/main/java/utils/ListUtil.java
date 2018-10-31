package utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 处理容器List的工具方法
 */
public class ListUtil {

    /**
     * 将源list分页
     * @param list
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();
        int page = (listSize + (pageSize-1))/ pageSize;
        List<List<T>> listArray = new ArrayList<>();
        for(int i=0;i<page;i++) {
            List<T> subList = new ArrayList<T>();
            for(int j=0;j<listSize;j++) {
                int pageIndex = ( (j + 1) + (pageSize-1) ) / pageSize;
                if(pageIndex == (i + 1)) {
                    subList.add(list.get(j));
                }
                if( (j + 1) == ((j + 1) * pageSize) ) {
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;
    }

    /**
     * rawData - toRemoveData
     * @param rawData
     * @param toRemoveData
     * @param <T>
     * @return
     */
    public static<T> Set<T> diffSet(List<T> rawData, List<T> toRemoveData){
        Set<T> rawDataSet = new HashSet<>(rawData);
        Set<T> toRemoveDataSet = new HashSet<>(toRemoveData);
        rawDataSet.removeAll(toRemoveDataSet);
        return rawDataSet;
    }


    /**
     * list 去重
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> List<T> removeDuplicate(List<T> datas){
        return new ArrayList<>(new HashSet<>(datas));
    }

    /**
     * 从 originList 抽取出 字段fieldName 的非重复List
     * @param originList
     * @param fieldName
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E, T> List<T> extractOneField(List<E> originList, String fieldName){
        Set<T> result = new HashSet<>();

        try {
            Class clazz = originList.get(0).getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            for (E origin : originList){
                T value = (T) field.get(origin);
                result.add(value);
            }
        } catch (Exception e){
            // do something
        }
        return new ArrayList<>(result);
    }
}
