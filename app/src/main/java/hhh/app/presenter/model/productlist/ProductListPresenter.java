package hhh.app.presenter.model.productlist;

import java.util.List;

import javax.inject.Inject;

import hhh.app.presenter.App;
import hhh.app.data.bean.Product;
import hhh.app.data.repository.ProductRepository;
import hhh.app.presenter.ui.base.BasePresenter;
import hhh.app.presenter.utils.L;
import rx.Subscriber;

/**
 * Created by hhh on 2016/10/28.
 */
public class ProductListPresenter extends BasePresenter<ProductListContract.IProductListView> implements ProductListContract.IProductListPresenter {
    private ProductListContract.IProductListView indexView;

    private ProductRepository repository;

    private Subscriber<List<Product>> subscriber=new Subscriber<List<Product>>() {
        @Override
        public void onCompleted() {
            indexView.showLoading(false);
        }

        @Override
        public void onError(Throwable e) {
            L.d(e.toString());
        }

        @Override
        public void onNext(List<Product> products) {
            if(products!=null){
                indexView.showProducts(products);
            }else{
                indexView.showNoProducts();
            }
        }
    };

    @Inject
    public ProductListPresenter(){
        repository= App.getApp().getProductRepositoryComponent().getProductRepository();
    }

    @Override
    public void loadProducts() {
        indexView.showLoading(true);
        repository.getProducts(subscriber);
    }

    @Override
    public void openProductDetail(Product p) {

    }

}
