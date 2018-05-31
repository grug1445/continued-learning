package tech.grug.anyTech;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by feichen on 2018/5/31.
 */
public class GuavaCache {


    public static void main(String[] args) throws Exception {
        GuavaCache testCache = new GuavaCache();
//        testCache.testWeakValues();
        testCache.testWeakKeys();
    }


    /**
     * 当cache中不存在要查找的entry的时候会自动执行用户自定义的加载逻辑，
     * 加载成功后将entry存入缓存并返回给用户(获取过程中调用方需要等待加载完成)未过期的entry，
     * 如果不存在或者已过期，则需要load，为防止多线程并发下重复加载，需要先锁定，
     * 获得加载资格的线程（获得锁的线程）创建一个LoadingValueRefrerence并放入map中，其他线程等待结果返回。
     *
     * @throws Exception
     */
    public void testLoad() throws Exception {
        LoadingCache<String, Object> loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) throws Exception {
                System.out.println("load value for key:" + key);
                return key + "";
            }
        });
        //第一次查询会执行cache的load方法,第二次直接从缓存获取
        System.out.println(loadingCache.get("key1"));
        System.out.println(loadingCache.get("key1"));
    }


    /**
     * 如果指定了cache的最大容量，那么当触及容量阈值的时候会根据 LRU策略自动删除entry
     *
     * @throws Exception
     */
    public void testEviction() throws Exception {
        //设置cache的最大size为5，并监听删除事件，一旦有缓存数据被删除则会通知监听
        Cache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(5).removalListener(notification -> {
            System.out.println(notification.getValue() + " is removed");
        }).build();
        //放入5个对象
        for (int i = 0; i < 5; i++) {
            cache.put("key" + i, i);
        }
        //放入第6个对象，此时缓存已满，会根据LRU策略删除最早的数据
        cache.put("key" + 10, 10);
    }

    /**
     * 如果指定了entry的最大存活时间，那么如果该entry从最后一次活跃时间到最大存活时间内没有再活跃过，
     * 接下来就会被从cache中删除，如果用户注册了RemoveListener，将会同时接收到数据被移除的通知
     *
     * @throws Exception
     */
    public void testTimeBasedEviction() throws Exception {
        //写入30秒后过期，并监听删除事件，一旦有缓存数据被删除则会通知监听
        Cache<String, Object> cache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS)
                //为了查看过期效果，这里讲并发数改为了1？
                .concurrencyLevel(1).removalListener(notification -> {
                    System.out.println(notification.getValue() +
                            " is removed at:" + System.currentTimeMillis() / 1000);
                }).build();
        //每隔10十秒一个对象，共放入5个对象,放入第4个对象时因为第1个已经过期则会删除第0个对象
        for (int i = 0; i <= 4; i++) {
            System.out.println("put key" + i + ":" + i + " at:" + System.currentTimeMillis() / 1000);
            cache.put("key" + i, i);
            Thread.sleep(12000);
        }
    }


    /**
     * cache中的key可以被包装成弱引用。弱引用的好处就是当jvm执行gc的时候会自动将该entry回收。
     * 如果一个对象只有WeakReference对象引用它，那么就可能被gc掉。
     * gc掉后WeakReference对象会进入ReferenceQueue队列。cache会利用RQ队列将entry移除掉。
     *
     * @throws Exception
     */
    public void testWeakKeys() throws Exception {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().concurrencyLevel(1)
                //缓存key使用WeakReference包装,一旦gc执行则回收该key，guava会自动移除key所对应的entry
                .weakKeys().removalListener(notification -> {
                    //注册监听，打印被回收的key对应的value
                    //此时打印的key为null(因为被gc掉了)，value为实际值
                    System.out.println("remove " + notification.getKey() + ":" + notification.getValue());
                }).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("load key:" + key);
                        return "v(" + key.toString() + ")";
                    }
                });
        int i = 0;
        while (true) {
            //通知jvm需要执行gc
            if (i % 10 == 0) {
                System.gc();
            }
            Thread.sleep(1000);
            loadingCache.get("bird" + (i++) + "");

            if (i == 10000) {
                break;
            }
        }
    }


    /**
     * cache中的value也可以被自动包装成弱引用或者软引用
     *
     * @throws Exception
     */
    public void testWeakValues() throws Exception {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().concurrencyLevel(1)
                //缓存value使用WeakReference包装,一旦gc执行则回收该value,guava会自动移除value所对应的entry
                .weakValues().removalListener(notification -> {
                    //注册监听，打印被回收的key对应的value
                    //此时打印的key为实际值，value为null(因为被gc掉了)
                    System.out.println("remove " + notification.getKey() + ":" + notification.getValue());
                }).build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("load key:" + key);
                        return "v(" + key.toString() + ")";
                    }
                });
        int i = 0;
        while (true) {
            //通知jvm需要执行gc
            if (i % 10 == 0) {
                System.gc();
            }
            Thread.sleep(1000);
            loadingCache.get("bird" + (i++) + "");
            if (i == 1000) {
                break;
            }
        }
    }


    /**
     * cache支持key命中或者不命中的统计
     *
     * @throws Exception
     */
    public void testStat() throws Exception {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                //开启统计功能
                .recordStats().maximumSize(15).build();
        for (int i = 0; i < 20; i++) {
            cache.put(i + "", i + "");
        }

        for (int i = 0; i < 30; i++) {
            cache.getIfPresent(i + "");
        }
        //输出：
        //CacheStats{hitCount=15, missCount=15, loadSuccessCount=0,
        // loadExceptionCount=0, totalLoadTime=0, evictionCount=5}
        System.out.println(cache.stats().toString());
    }

}
