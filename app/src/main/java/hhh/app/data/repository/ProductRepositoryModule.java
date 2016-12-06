package hhh.app.data.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hhh.app.data.repository.datasource.IDataSource;
import hhh.app.data.repository.datasource.LocalIDataSource;
import hhh.app.data.repository.datasource.RemoteIDataSource;

/**
 * Created by hhh on 2016/11/23.
 */
@Module
public class ProductRepositoryModule {
    @Singleton
    @Provides
    @Remote
    IDataSource provideProductRemoteDataSource(){
        return new RemoteIDataSource();
    }

    @Singleton
    @Provides
    @Local
    IDataSource provideProductLocalDataSource(){
        return new LocalIDataSource();
    }
}
