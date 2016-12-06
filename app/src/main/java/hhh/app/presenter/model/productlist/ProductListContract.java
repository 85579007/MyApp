package hhh.app.presenter.model.productlist;

import java.util.List;

import hhh.app.data.bean.Product;
import hhh.app.presenter.ui.base.IPresenter;
import hhh.app.presenter.ui.base.IView;

/**
 * Created by hhh on 2016/10/28.
 */
public interface ProductListContract {
    interface IProductListView extends IView {
        void showProducts(List products);
        void showNoProducts();
        void showLoading(boolean isShow);
    }
    interface IProductListPresenter extends IPresenter<IProductListView> {
        void loadProducts();
        void openProductDetail(Product p);
    }
}
