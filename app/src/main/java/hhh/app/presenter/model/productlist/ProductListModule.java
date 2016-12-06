package hhh.app.presenter.model.productlist;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hhh on 2016/11/25.
 */
@Module
public class ProductListModule {
    private ProductListContract.IProductListView view;

    public ProductListModule(ProductListContract.IProductListView view) {
        this.view = view;
    }

    @Provides
    ProductListContract.IProductListView provideProductListView(){
        return view;
    }
}
