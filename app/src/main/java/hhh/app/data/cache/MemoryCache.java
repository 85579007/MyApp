package hhh.app.data.cache;

import android.text.TextUtils;
import android.util.LruCache;

import com.google.gson.Gson;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by hhh on 2016/12/6.
 */
public class MemoryCache implements ICache {
    private LruCache<String,String> mCache;

    public MemoryCache() {
        int maxMemory=(int)Runtime.getRuntime().maxMemory();
        int cacheSize=maxMemory/8;
        mCache=new LruCache<String,String>(cacheSize){
            @Override
            protected int sizeOf(String key, String value) {
                return value.getBytes().length;
            }
        };
    }

    @Override
    public <U> Observable<U> get(final String key, final Class<U> cls) {
        return Observable.create(new Observable.OnSubscribe<U>() {
            @Override
            public void call(Subscriber<? super U> subscriber) {
                String result=mCache.get(key);
                if(subscriber.isUnsubscribed()){
                    return;
                }
                if(result!=null){
                    U u=new Gson().fromJson(result,cls);
                    subscriber.onNext(u);
                }else{
                    subscriber.onNext(null);
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public <U> void put(String key, U entity) {
        if(null!=entity){
            mCache.put(key,new Gson().toJson(entity));
        }
    }

    public void clearMemory(String key){
        mCache.remove(key);
    }
}
