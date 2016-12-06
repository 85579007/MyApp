package hhh.app.presenter;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import hhh.app.data.net.ApiServiceModule;
import hhh.app.data.repository.DaggerProductRepositoryComponent;
import hhh.app.data.repository.ProductRepositoryComponent;
import hhh.app.data.repository.ProductRepositoryModule;
import hhh.app.presenter.utils.Code;

/**
 * Created by Administrator on 2016/10/23 0023.
 */
public class App extends Application {
    private static App app;
    private Map<String,Activity> map=null;
    private AppComponent appComponent;
    private ProductRepositoryComponent productRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        appComponent= DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule(Code.serverUrl))
                .build();

        productRepositoryComponent= DaggerProductRepositoryComponent.builder()
                .productRepositoryModule(new ProductRepositoryModule())
                .build();

    }

    public ProductRepositoryComponent getProductRepositoryComponent() {
        return productRepositoryComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    public static App getApp(){
        return app;
    }

    public void put(String string,Activity activity){
        if(map==null){
            map=new HashMap<String, Activity>();
        }
        map.put(string,activity);
    }

    public Activity get(String string){
        return map.get(string);
    }

    public void closeAll(){
        Set<String> names=map.keySet();
        for(String s:names){
            finishActivity(map.get(s));
        }
        map.clear();;
    }

    public void remove(String name){
        finishActivity(map.get(name));
    }

    private final void finishActivity(Activity activity){
        if(activity!=null&&!activity.isFinishing()){
            activity.finish();
        }
    }

}
