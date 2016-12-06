package hhh.app.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import hhh.app.data.bean.Product;
import hhh.app.data.repository.datasource.ProductDataSource;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hhh on 2016/10/28.
 */
@Singleton
public class ProductRepository implements ProductDataSource {

    private ProductDataSource remoteDataSource;
    private ProductDataSource localDataSource;

    @Inject
    public ProductRepository(@Remote ProductDataSource remoteDataSource, @Local ProductDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }


    @Override
    public void getProducts(Subscriber<List<Product>> subscriber) {
        remoteDataSource.getProducts(subscriber);
    }

    @Override
    public void getProduct(Subscriber<Product> subscriber, int productId) {
        remoteDataSource.getProduct(subscriber,productId);
    }

    @Override
    public Observable<List<Product>> productList() {
        return null;
    }

    @Override
    public Observable<Product> productDetail() {
        return null;
    }
}
