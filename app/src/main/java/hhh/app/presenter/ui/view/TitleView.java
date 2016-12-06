package hhh.app.presenter.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hhh.app.R;

/**
 * Created by hhh on 2016/10/25.
 */
public class TitleView extends RelativeLayout {
    TextView title;

    public TitleView(Context context) {
        super(context);
        init(null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_title,this);
        title= (TextView) findViewById(R.id.title_title);

        if(attrs==null)return ;
        TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.TitleView);
        int N=a.getIndexCount();
        for(int i=0;i<N;i++){
            int index=a.getIndex(i);
            switch (index){
                case R.styleable.TitleView_tv_title:
                    setTitle(a.getString(index));
                    break;
                case R.styleable.TitleView_tv_color:
                    setTitleTextColor(a.getColor(index, Color.BLACK));
                    break;
                case R.styleable.TitleView_tv_titleTextSize:
                    setTitleTextSize(a.getDimensionPixelSize(index,16));
                    break;
            }
        }
        a.recycle();
    }

    public void setTitle(String s){
        title.setText(s);
    }

    public void setTitleTextColor(int color){
        title.setTextColor(color);
    }

    public void setTitleTextSize(int size){
        title.setTextSize(size);
    }
}
