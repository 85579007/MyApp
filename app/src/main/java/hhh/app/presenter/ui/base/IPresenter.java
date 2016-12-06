package hhh.app.presenter.ui.base;

/**
 * Created by hhh on 2016/11/15.
 */
public interface IPresenter<V extends IView> {
    void attachView(V v);
    void detachView();
}
