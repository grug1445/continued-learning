package tech.grug.anyTech;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by feichen on 2018/5/28.
 */
public class GuavaCacheTest {

    public static void main(String[] args) throws Exception {
        LoadingCache<String, String> cache = CacheBuilder
                .newBuilder()
                .maximumSize(3)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        System.out.println("load " + s);
                        return s;
                    }
                });

        System.out.println("get from cache " + cache.get("mike"));
        System.out.println("get from cache " + cache.get("mike"));
        System.out.println("get from cache " + cache.get("kate"));
        System.out.println("get from cache " + cache.get("hello"));
        System.out.println("get from cache " + cache.get("haha"));


    }
}
