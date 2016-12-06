package hhh.app.data.repository.datasource;

import java.util.List;

import hhh.app.data.bean.MyResult;
import hhh.app.data.bean.Product;
import hhh.app.data.net.ApiService;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hhh on 2016/10/28.
 */

public class RemoteIDataSource implements IDataSource {
//    @Inject Retrofit retrofit;
//    @Inject OkHttpClient okHttpClient;
//    @Inject Cache cache;
    ApiService apiService;

    public RemoteIDataSource() {
//        Gson gson=new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                .create();
//        File cachedir=new File(App.getApp().getApplicationContext().getCacheDir().getAbsolutePath(),"HttpCache");
//        cache=new Cache(cachedir,10*1024*1024);
//
//        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        okHttpClient=new OkHttpClient.Builder()
//                .cache(cache)
////                .addInterceptor(loggingInterceptor)
//                .connectTimeout(5, TimeUnit.SECONDS)
//                .build();
////        okHttpClient.interceptors().add(loggingInterceptor);
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(Code.serverUrl)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        apiService = retrofit.create(ApiService.class);
    }

    private void getData(Subscriber subscriber) {
        apiService.getProducts()
                   .map(new Func1<MyResult<List<Product>>, List<Product>>() {
                       @Override
                       public List<Product> call(MyResult<List<Product>> listMyResult) {
                           return listMyResult.data;
                       }
                   })
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(subscriber);
    }


    @Override
    public <U> Observable<List<U>> getList() {
        return null;
    }

    @Override
    public <U> Observable<U> getDetail() {
        return null;
    }
}
