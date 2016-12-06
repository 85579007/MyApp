package hhh.app.presenter.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by hhh on 2016/11/1.
 */
public class MyScrollView extends ScrollView {

    public interface OnScrollListener{
        public void onScroll(int scrollY);
    }

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener!=null){
            onScrollListener.onScroll(t);
        }
    }
}
