package hhh.app.presenter.model.productdetail;

import hhh.app.data.bean.Product;
import hhh.app.presenter.ui.base.IPresenter;
import hhh.app.presenter.ui.base.IView;
import rx.Subscriber;

/**
 * Created by hhh on 2016/10/31.
 */
public interface ProductDetailContract {
    interface IProductDetailView extends IView {
        void showProductDetail(Product p);
        void showLoading(boolean isShow);
    }
    interface IProductDetailPresenter extends IPresenter<IProductDetailView>{
        void loadProduct(Subscriber<Product> sp);
    }
}
