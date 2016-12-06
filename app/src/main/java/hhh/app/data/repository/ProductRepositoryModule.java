package hhh.app.data.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hhh.app.data.repository.datasource.IProductDataSource;
import hhh.app.data.repository.datasource.LocalIProductDataSource;
import hhh.app.data.repository.datasource.RemoteIProductDataSource;

/**
 * Created by hhh on 2016/11/23.
 */
@Module
public class ProductRepositoryModule {
    @Singleton
    @Provides
    @Remote
    IProductDataSource provideProductRemoteDataSource(){
        return new RemoteIProductDataSource();
    }

    @Singleton
    @Provides
    @Local
    IProductDataSource provideProductLocalDataSource(){
        return new LocalIProductDataSource();
    }
}
