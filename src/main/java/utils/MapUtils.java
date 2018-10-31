/**
 * @(#)MapUtils.java, 2016年7月16日.
 *
 * Copyright 2016 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package utils;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Map常用工具类
 * 
 */
public class MapUtils {


    /**
     * 将 k,v 放入 map 中
     * 
     * @param map
     * @param key
     * @param value
     */
    public static <K, V> void putIfAbsent(Map<K, List<V>> map, K key, V value) {
        if (map == null) {
            return;
        }
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            List<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        }
    }

    /**
     * 将两个map 按key 相加，结果存于 targetMap
     * @param targetMap
     * @param sourceMap
     * @param <K>
     */
    public static <K> void mapAdd(Map<K, Integer> targetMap, Map<K, Integer> sourceMap){
        if (sourceMap != null){
            for (K key : sourceMap.keySet()){
                Integer originValue = targetMap.get(key) == null ? 0 : targetMap.get(key);
                targetMap.put(key, originValue + sourceMap.get(key));
            }
        }
    }

    /**
     * 将 originMap 中的K V都转成String格式放入targetMap中
     * @param targetMap
     * @param originMap
     * @param
     */
    public static <K, V> void convertStringAndPut(Map<String, String> targetMap, Map<K, V> originMap){
        if (originMap != null){
            for (K key : originMap.keySet()){
                targetMap.put(String.valueOf(key), String.valueOf(originMap.get(key)));
            }
        }
    }

    /**
     * 从 map 中读取一个值，如果为 null 则返回 defaultValue
     * @param map
     * @param key
     * @param defaultValue
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> String getValue(Map<K, V> map, K key, String defaultValue){
        if (map == null || map.get(key) == null){
            return defaultValue;
        }
        return String.valueOf(map.get(key));
    }

    /**
     * 将List 转成 Map， 按其中某一字段抽取
     * @param originList
     * @param fieldName     要做为key的字段名
     * @param <K>
     * @param <V>
     * @return
     * @throws Exception
     */
    public static <K,V> Map<K, List<V>> extractByDeclaredField(List<V> originList, String fieldName){
        Map<K, List<V>> result = new HashMap<>();
        if (originList != null && StringUtils.isNotBlank(fieldName)){
            try {
                Class clazz = originList.get(0).getClass();
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);

                for (V origin : originList){
                    K key = (K) field.get(origin);
                    putIfAbsent(result, key, origin);
                }
            }catch (Exception e){
                // do something
            }
        }
        return result;
    }


}
