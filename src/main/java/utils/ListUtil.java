package com.netease.yanxuan.teddy.core.util;

import java.lang.reflect.Field;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.netease.yanxuan.teddy.core.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

public class ListUtil {

    /**
     * 将集合对象转成ArrayList
     * @author youzhihao
     */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> list = new ArrayList<E>();
        for (E item : collection) {
            list.add(item);
        }
        return list;
    }

    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();
        int page = (listSize + (pageSize-1))/ pageSize;
        List<List<T>> listArray = new ArrayList<List<T>>();
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


    public static<T> Set<T> diffSet(List<T> rawData, List<T> toRemoveData){
        Set<T> rawDataSet = new HashSet<>(rawData);
        Set<T> toRemoveDataSet = new HashSet<>(toRemoveData);
        rawDataSet.removeAll(toRemoveDataSet);
        return rawDataSet;
    }


    public static <T> List<T> removeDuplicate(List<T> datas){
        Set<T> rawDataSet = new HashSet<>();
        List<T> newData = new ArrayList<>();
        for(T data: datas){
            if(rawDataSet.contains(data)){
                continue;
            }else {
                newData.add(data);
                rawDataSet.add(data);
            }
        }
        return newData;
    }


    public static<T> List<T> retainSame(List<T> rawData, List<T> toRetainData){
        if(rawData.size() == toRetainData.size()){
            return rawData;
        }
        Set<T> rawDataSet = new HashSet<>(rawData);
        Set<T> toRemoveDataSet = new HashSet<>(toRetainData);
        rawDataSet.retainAll(toRemoveDataSet);
        List<T> newData = new ArrayList<>();
        for(T data : rawData){
            if(rawDataSet.contains(data)){
                newData.add(data);
            }
        }
        return newData;
    }



    public static<T> Set<T> getRepeatSet(List<T> datas){
        Set<T> skuIdSet = new HashSet();
        Set<T> skuIdRepeatSet = new HashSet();
        for(T data: datas){
            if(skuIdSet.contains(data)){
                skuIdRepeatSet.add(data);
            }else {
                skuIdSet.add(data);
            }
        }
        return skuIdRepeatSet;
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
            throw new ServiceException("ListUtil.extractOneField 反射异常", e);
        }
        return new ArrayList<>(result);
    }
}
