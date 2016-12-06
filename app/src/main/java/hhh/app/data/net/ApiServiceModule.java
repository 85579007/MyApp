package hhh.app.data.net;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hhh on 2016/11/22.
 */
@Module
public class ApiServiceModule {
    String baseUrl;

    public ApiServiceModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Cache provideCache(Application application){
        Cache cache=new Cache(application.getCacheDir(),10*1024*1024);
        return cache;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson,OkHttpClient okHttpClient){
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
