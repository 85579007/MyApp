package hhh.app.data.cache;

import rx.Observable;

/**
 * Created by hhh on 2016/12/5.
 */
public interface ICache {
    <U> Observable<U> get(String key, Class<U> cls);

    <U> void put(String key,U entity);

}
