package com.rayoy.learn.guava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.apache.commons.lang3.RandomUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @createTime 2019-04-22 15:15
 */
public class GuavaCache {

    static LoadingCache<WorkflowCacheKey, String> workflowCache = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.MINUTES) // 缓存项在给定时间内（60min）没有被写访问（创建或覆盖），则回收
            .maximumSize(100) // 最多缓存100项
            .recordStats() // 记录统计内容
            .build(new CacheLoader<WorkflowCacheKey, String>() {
                public String load(WorkflowCacheKey key) throws Exception {
                    return "default" + key.id;
                }
            });

    static Cache<String, WorkflowCacheKey> keyCache = CacheBuilder.newBuilder()
            .maximumSize(2).expireAfterWrite(2, TimeUnit.MINUTES).recordStats().build();

    public static String startWorkflow(Long id, String name, String avatar) {
        /*
         * 注意：
         * 如果以一个新建的对象做为key的话，因为每次都是新建一个对象，所以这样的话，实际上每次访问key都是不同的，即每次访问都是重新进行缓存;
         * 但是实际上，我们想要根据对象的属性来判断对象是否相等，只需要根据这些属性重写对象的hashCode与equals方法即可，
         * 所以重写了AdminCacheKey类的hashCode和equals方法，这样，每次访问的话，就会以每个条件是否相等来判断对象（即key）是否相等了，这一块儿的缓存就会起作用了
         */
        String workflowId = "";
        WorkflowCacheKey cacheKey = new WorkflowCacheKey(id, name, avatar);
        System.out.println(cacheKey);
        try {
            workflowId = workflowCache.get(cacheKey);
        } catch (ExecutionException e) {
            System.out.println("=======================error=======================");
            e.printStackTrace();
        }

        return workflowId;
    }

    public static void main(String[] args) throws ExecutionException {
        //        test1();
        test2();
    }

    private static void test1() {
        LongStream.range(1, 10).forEach(i -> {
            String workflowId = GuavaCache.startWorkflow(i % 4, "name", "avatar");
            System.out.println("workflowId: " + workflowId);
        });
        System.out.println(workflowCache.stats());
    }

    private static void test2() throws ExecutionException {
        LongStream.range(1, 10).forEach(i -> {
            try {
                System.out.println(getKey());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(keyCache.stats());
        keyCache.invalidateAll();
        System.out.println(getKey());
        keyCache.invalidateAll();
        System.out.println(getKey());
        System.out.println(keyCache.stats());
    }

    private static WorkflowCacheKey getKey() throws ExecutionException {
        WorkflowCacheKey workflowCacheKey = keyCache.get("key", () -> {
            Long id = RandomUtils.nextLong();
            return new WorkflowCacheKey(id, "name" + id, "avatar" + id);
        });
        return workflowCacheKey;
    }
}
