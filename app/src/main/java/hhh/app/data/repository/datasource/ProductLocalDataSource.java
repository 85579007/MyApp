package hhh.app.data.repository.datasource;

import java.util.List;

import hhh.app.data.bean.Product;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hhh on 2016/10/28.
 */

public class ProductLocalDataSource implements ProductDataSource {

    public ProductLocalDataSource() {
    }

    @Override
    public void getProducts(Subscriber<List<Product>> subscriber) {

    }

    @Override
    public void getProduct(Subscriber<Product> subscriber, int productId) {

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
