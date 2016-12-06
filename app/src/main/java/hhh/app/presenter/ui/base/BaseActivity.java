package hhh.app.presenter.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import hhh.app.presenter.App;
import hhh.app.presenter.model.productlist.ProductListActivity;
import hhh.app.R;
import hhh.app.presenter.utils.T;


/**
 * Created by hhh on 2016/6/12.
 */
public class BaseActivity extends AppCompatActivity implements IView {

    private boolean isExit;
    private Handler handler=new Handler();
    private ProgressDialog loading;
    private Unbinder mUnbinder;

//    protected P mPrensenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder= ButterKnife.bind(this);

//        createPresenter();
//        if(mPrensenter!=null){
//            mPrensenter.attachView(this);
//        }
        App.getApp().put(getClass().getName(),this);
    }

//    protected abstract void createPresenter();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(getClass().getName().equals(ProductListActivity.class.getName())){
                if(isExit){
                    App.getApp().closeAll();
                }else{
                    isExit=true;
                    T.show(this, R.string.exit);
                    handler.postDelayed(r,2000);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private Runnable r=new Runnable() {
        @Override
        public void run() {
            isExit=false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
/*        if(mPrensenter!=null){
            mPrensenter.detachView();
        }*/
        mUnbinder.unbind();
        App.getApp().remove(getClass().getName());
    }

    public void showLoading(){
        if(loading==null){
            //loading=new AlertDialog.Builder(this).create();
            loading=new ProgressDialog(this);
            loading.setMessage("加载中");
//            loading.setCanceledOnTouchOutside(false);
//            Window w=loading.getWindow();
//            w.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        loading.show();
    }

    public void dismissLoading(){
        loading.dismiss();
    }
}
