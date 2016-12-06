package hhh.app.presenter;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import hhh.app.data.net.ApiService;
import hhh.app.data.net.ApiServiceModule;

/**
 * Created by hhh on 2016/11/23.
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
    Application getApplication();
    ApiService getApiService();
}
