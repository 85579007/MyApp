package hhh.app.data.repository.datasource;

import java.util.List;

import hhh.app.data.bean.Product;
import hhh.app.data.net.ApiImp;
import rx.Observable;

/**
 * Created by hhh on 2016/10/28.
 */

public class RemoteIProductDataSource implements IProductDataSource {
    ApiImp apiImp;

    public RemoteIProductDataSource(String url) {
        apiImp =new ApiImp(url);
    }

    @Override
    public Observable<List<Product>> getList() {
        return apiImp.getProductList();
    }

    @Override
    public Observable<Product> getDetail(int id) {
        return apiImp.getProductDetail(id);
    }
}
