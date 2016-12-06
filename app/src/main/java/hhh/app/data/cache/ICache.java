package hhh.app.data.cache;

import rx.Observable;

/**
 * Created by hhh on 2016/12/5.
 */
public interface ICache {
    <U> Observable<U> get(final int uid);

    <U> void put(U entity);

    boolean isCached(final int uid);

    boolean isExpired();

    void evictAll();
}
