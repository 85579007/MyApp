package hhh.app.data.repository;

import javax.inject.Singleton;

import dagger.Component;
import hhh.app.presenter.AppModule;

/**
 * Created by hhh on 2016/11/23.
 */
@Singleton
@Component(modules = {ProductRepositoryModule.class, AppModule.class})
public interface ProductRepositoryComponent {
    ProductRepository getProductRepository();
}
