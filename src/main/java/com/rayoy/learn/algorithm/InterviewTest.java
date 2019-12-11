package com.rayoy.learn.algorithm;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author wanglei60
 * @createTime 2019-05-06 15:15
 */
public class InterviewTest {
    public static void main(String[] args) {
        Integer a = new Integer(295);
        Integer b = new Integer(295);
        int c = 295;

        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a == c);

        HashMap hashMap = new HashMap();
        Map hashTable = new Hashtable();
        hashMap.put(null, null);
        hashTable.put(null, "value");
        hashTable.put("key", null);
        System.out.println(hashMap.get(null));
    }
}
