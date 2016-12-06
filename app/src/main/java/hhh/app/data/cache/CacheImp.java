package hhh.app.data.cache;

import android.content.Context;

import java.io.File;

import hhh.app.data.exception.NotFoundException;
import hhh.app.data.cache.serializer.JsonSerializer;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class CacheImp implements ICache {
    private static final String DEFAULT_FILE_NAME="product_";
    private static final String PREFERANCE_FILE_NAME="hhh.app.SETTING";
    private static final String KEY_NAME="last_update_time";
    private static final long EXPIRE_TIME=60*10*1000;

    private FileManager fileManager;
    private File cacheDir;
    private Context context;
    private JsonSerializer serializer;

    public CacheImp(FileManager fileManager, Context context, JsonSerializer serializer) {
        this.fileManager = fileManager;
        this.cacheDir = context.getCacheDir();
        this.context = context;
        this.serializer = serializer;
    }

    @Override
    public <U> Observable<U> get(final int uid) {
        Observable observable=Observable.create(new Observable.OnSubscribe<U>() {
            @Override
            public void call(Subscriber<? super U> subscriber) {
                File file=CacheImp.this.buildFile(uid);
                String content=CacheImp.this.fileManager.readFileContent(file);
                U u=CacheImp.this.serializer.deserialize(content);
                if(u!=null){
                    subscriber.onNext(u);
                    subscriber.onCompleted();
                }else{
                    subscriber.onError(new NotFoundException());
                }
            }
        });
        return null;
    }

    @Override
    public <U> void put(U entity) {
        if(entity!=null){
            File file=buildFile(Integer.parseInt(entity.toString()));
            if(!fileManager.exists(file)) {
                fileManager.writeToFile(file, serializer.serialize(entity));
                setLastUpdateTime();
            }
        }
    }

    @Override
    public boolean isCached(int uid) {
        File file=buildFile(uid);
        return fileManager.exists(file);
    }

    @Override
    public boolean isExpired() {
        long cur=System.currentTimeMillis();
        long last=getLastUpdateTime();
        return (cur-last)>EXPIRE_TIME?true:false;
    }

    @Override
    public void evictAll() {

    }

    private File buildFile(int uid){
        StringBuffer buffer=new StringBuffer();
        buffer.append(cacheDir.getPath());
        buffer.append(File.separator);
        buffer.append(DEFAULT_FILE_NAME);
        buffer.append(uid);
        return new File(buffer.toString());
    }

    private void setLastUpdateTime(){
        Long l=System.currentTimeMillis();
        fileManager.writeToPreferences(context,PREFERANCE_FILE_NAME,KEY_NAME,l);
    }

    private long getLastUpdateTime(){
        return fileManager.getFromPreferences(context,PREFERANCE_FILE_NAME,KEY_NAME);
    }
}