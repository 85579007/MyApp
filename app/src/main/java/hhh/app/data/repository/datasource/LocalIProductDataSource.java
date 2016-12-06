package hhh.app.data.repository.datasource;

import android.content.Context;

import java.util.List;

import hhh.app.data.bean.Product;
import hhh.app.data.cache.CacheImp;
import rx.Observable;

/**
 * Created by hhh on 2016/10/28.
 */

public class LocalIProductDataSource implements IProductDataSource {
    private CacheImp cacheImp;

    public LocalIProductDataSource(Context context) {
        cacheImp=new CacheImp(context);
    }

    @Override
    public Observable<List<Product>> getList() {
        return cacheImp.getList();
    }

    @Override
    public Observable<Product> getDetail(int id) {
        return cacheImp.get(id);
    }
}
