package hhh.app.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import hhh.app.data.bean.Product;
import hhh.app.data.repository.datasource.IDataSource;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hhh on 2016/10/28.
 */
@Singleton
public class ProductRepository implements IDataSource {

    private IDataSource remoteDataSource;
    private IDataSource localDataSource;

    @Inject
    public ProductRepository(@Remote IDataSource remoteDataSource, @Local IDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
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
