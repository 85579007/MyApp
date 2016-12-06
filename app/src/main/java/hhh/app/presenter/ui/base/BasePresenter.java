package hhh.app.presenter.ui.base;

/**
 * Created by hhh on 2016/11/16.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {
    public V view;
//    private CompositeSubscription compositeSubscription;

    @Override
    public void attachView(V v) {
        view=v;
    }

    @Override
    public void detachView() {
        view=null;
//        onUnsubscribe();
    }

//    public void onUnsubscribe() {
//        if(compositeSubscription!=null&&compositeSubscription.hasSubscriptions()){
//            compositeSubscription.unsubscribe();
//        }
//    }
//
//    public void addSubscription(Observable observable, Subscriber subscriber){
//        if(compositeSubscription==null){
//            compositeSubscription=new CompositeSubscription();
//        }
//        compositeSubscription.add(
//                observable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(subscriber)
//        );
//    }
}
