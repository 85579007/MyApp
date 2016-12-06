package hhh.app.data.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import hhh.app.data.bean.MyResult;
import hhh.app.data.bean.Product;
import hhh.app.data.repository.datasource.IDataSource;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by hhh on 2016/12/6.
 */
public class ApiConnection {
    private static final int CACHE_SIZE=10*1024*1024;
    private String url;
    private Context context;
    private Gson gson;
    private ApiService apiService;
    private Cache cache;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public ApiConnection(String url) {
        this.url = url;
        gson=new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        cache=new Cache(context.getCacheDir(),CACHE_SIZE);
        okHttpClient=new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService=retrofit.create(ApiService.class);
    }

    public Observable<List<Product>> getProductList(){
        return apiService.getProducts()
                .flatMap(new Func1<MyResult<List<Product>>, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(MyResult<List<Product>> listMyResult) {
                        return null;
                    }
                });
    }

    public Observable<Product> getProductDetail(int id){
        return apiService.getProductById(id)
                .flatMap(new Func1<MyResult<Product>, Observable<Product>>() {
                    @Override
                    public Observable<Product> call(MyResult<Product> productMyResult) {
                        return null;
                    }
                });
    }
}
