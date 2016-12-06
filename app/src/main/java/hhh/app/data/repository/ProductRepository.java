package hhh.app.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import hhh.app.data.bean.Product;
import hhh.app.data.repository.datasource.IProductDataSource;
import rx.Observable;

/**
 * Created by hhh on 2016/10/28.
 */
@Singleton
public class ProductRepository implements IProductDataSource {

    private IProductDataSource remoteDataSource;
    private IProductDataSource localDataSource;

    @Inject
    public ProductRepository(@Remote IProductDataSource remoteDataSource, @Local IProductDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }


    @Override
    public Observable<List<Product>> getList() {
        return null;
    }

    @Override
    public Observable<Product> getDetail(int id) {
        return null;
    }
}
