package hhh.app.presenter.model.productdetail;

import hhh.app.data.bean.Product;
import hhh.app.presenter.ui.base.BasePresenter;
import rx.Subscriber;

/**
 * Created by hhh on 2016/10/31.
 */
public class ProductDetailPresenter extends BasePresenter<ProductDetailContract.IProductDetailView> implements ProductDetailContract.IProductDetailPresenter{
    @Override
    public void loadProduct(Subscriber<Product> sp) {

    }
}
