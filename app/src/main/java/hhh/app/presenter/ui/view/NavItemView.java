package hhh.app.presenter.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hhh.app.R;

/**
 * Created by Administrator on 2016/10/27 0027.
 */
public class NavItemView extends RelativeLayout {
    private TextView iv_txt;
    private ImageView iv_img;
    private TextView iv_tr_txt;

    public NavItemView(Context context) {
        super(context);
        init(null);
    }

    public NavItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NavItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_nav_item,this);
        iv_txt= (TextView) findViewById(R.id.ni_txt);
        iv_tr_txt= (TextView) findViewById(R.id.ni_tr_txt);
        iv_img= (ImageView) findViewById(R.id.ni_img);
        if(attrs==null){
            return;
        }

    }

}
