package me.mushen.orcas.util;

import java.util.Collection;
import java.util.Map;

/**
 * @Desc
 * @Author Remilia
 * @Create 2017-01-17
 */
public class CollectionMaps {
    /**
     * 判断集合是否为空
     * @param collection 集合
     * @param <E>
     * @return
     */
    public static <E> boolean isNullOrEmpty(Collection<E> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断键值对是否为空
     * @param map 键值对
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isNullOrEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

    public static <T> boolean isNullOrEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
