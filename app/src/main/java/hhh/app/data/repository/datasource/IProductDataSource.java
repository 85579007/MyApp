package hhh.app.data.repository.datasource;

import java.util.List;

import hhh.app.data.bean.Product;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hhh on 2016/10/28.
 */
public interface IProductDataSource {
    Observable<List<Product>> getList();
    Observable<Product> getDetail(int id);
}
