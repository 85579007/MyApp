package hhh.app.data.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hhh.app.data.repository.datasource.ProductDataSource;
import hhh.app.data.repository.datasource.ProductLocalDataSource;
import hhh.app.data.repository.datasource.ProductRemoteDataSource;

/**
 * Created by hhh on 2016/11/23.
 */
@Module
public class ProductRepositoryModule {
    @Singleton
    @Provides
    @Remote
    ProductDataSource provideProductRemoteDataSource(){
        return new ProductRemoteDataSource();
    }

    @Singleton
    @Provides
    @Local
    ProductDataSource provideProductLocalDataSource(){
        return new ProductLocalDataSource();
    }
}
