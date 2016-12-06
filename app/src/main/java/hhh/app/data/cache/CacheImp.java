package hhh.app.data.cache;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import hhh.app.data.exception.NotFoundException;
import hhh.app.data.cache.serializer.JsonSerializer;
import hhh.app.data.executor.JobExecutor;
import hhh.app.data.executor.ThreadExecutor;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    private ThreadExecutor executor;

    public CacheImp(Context context) {
        this(new FileManager(),context,new JsonSerializer(),new JobExecutor());
    }

    public CacheImp(FileManager fileManager, Context context, JsonSerializer serializer, ThreadExecutor executor) {
        this.fileManager = fileManager;
        this.cacheDir = context.getCacheDir();
        this.context = context;
        this.serializer = serializer;
        this.executor=executor;
    }

    @Override
    public <U> Observable<U> get(final int uid) {
        return Observable.create(new Observable.OnSubscribe<U>() {
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
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public <U> Observable<List<U>> getList(){
        return Observable.create(new Observable.OnSubscribe<List<U>>() {
            @Override
            public void call(Subscriber<? super List<U>> subscriber) {
                String name=new TypeToken<U>(){}.getType().getClass().getName();
                File file=CacheImp.this.buildFile(name);
                String content=CacheImp.this.fileManager.readFileContent(file);
                List<U> list=CacheImp.this.serializer.deserialize(content);
                if(list!=null){
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                }else{
                    subscriber.onError(new NotFoundException());
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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

    private File buildFile(String uid){
        StringBuffer buffer=new StringBuffer();
        buffer.append(cacheDir.getPath());
        buffer.append(File.separator);
        buffer.append(DEFAULT_FILE_NAME);
        buffer.append(uid);
        return new File(buffer.toString());
    }

    private File buildFile(int uid){
        return buildFile(String.valueOf(uid));
    }

    private void setLastUpdateTime(){
        Long l=System.currentTimeMillis();
        fileManager.writeToPreferences(context,PREFERANCE_FILE_NAME,KEY_NAME,l);
    }

    private long getLastUpdateTime(){
        return fileManager.getFromPreferences(context,PREFERANCE_FILE_NAME,KEY_NAME);
    }

    private void executeAsyn(Runnable runnable){

    }

    private static class CacheWriter implements Runnable{
        private final FileManager fileManager;
        private final File file;
        private final String content;

        public CacheWriter(FileManager fileManager, File file, String content) {
            this.fileManager = fileManager;
            this.file = file;
            this.content = content;
        }

        @Override
        public void run() {
            fileManager.writeToFile(file,content);
        }
    }

    private static class CacheEvictor implements Runnable{
        private final FileManager fileManager;
        private final File cacheDir;

        public CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            fileManager.clearDirectory(cacheDir);
        }
    }
}
