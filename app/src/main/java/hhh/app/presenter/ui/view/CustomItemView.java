package hhh.app.presenter.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hhh.app.R;

/**
 * Created by hhh on 2016/11/8.
 */
public class CustomItemView extends RelativeLayout {
    ImageView ci_img;
    ImageView ci_arraw;
    TextView ci_label;
    TextView ci_mod;

    public CustomItemView(Context context) {
        super(context);
        init(null);
    }

    public CustomItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        LayoutInflater.from(this.getContext()).inflate(R.layout.custom_item,this);
        ci_img= (ImageView) findViewById(R.id.ci_img);
        ci_arraw= (ImageView) findViewById(R.id.ci_arraw);
        ci_label= (TextView) findViewById(R.id.ci_label);
        ci_mod= (TextView) findViewById(R.id.ci_mod);

        TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.CustomItemView);
        int N=a.getIndexCount();
        for(int i=0;i<N;i++){
            int index=a.getIndex(i);
            switch (index){
                case R.styleable.CustomItemView_ci_img:
                    setImg(a.getDrawable(index));
                    break;
                case R.styleable.CustomItemView_ci_label:
                    setLabel(a.getString(index));
                    break;
                case R.styleable.CustomItemView_ci_mod:
                    setMod(a.getString(index));
                    break;
                case R.styleable.CustomItemView_ci_label_color:
                    setLabelColor(a.getColor(index, Color.BLACK));
                    break;
                case R.styleable.CustomItemView_ci_mod_color:
                    setModColor(a.getColor(index,Color.GRAY));
                    break;
                case R.styleable.CustomItemView_ci_bg_color:
                    setBgColor(a.getColor(index,Color.WHITE));
                    break;
                case R.styleable.CustomItemView_ci_label_size:
                    setLabelFontSize(a.getDimension(index,getContext().getResources().getDimension(R.dimen.font_size_16)));
                    break;
                case R.styleable.CustomItemView_ci_mod_size:
                    setModFontSize(a.getDimension(index,getContext().getResources().getDimension(R.dimen.font_size_14)));
                    break;
                case R.styleable.CustomItemView_ci_img_show:
                    setVisibility(ci_img,a.getInt(index,2));
                    break;
                case R.styleable.CustomItemView_ci_mod_show:
                    setVisibility(ci_mod,a.getInt(index,2));
                    break;
                case R.styleable.CustomItemView_ci_arraw_show:
                    setVisibility(ci_arraw,a.getInt(index,2));
                    break;
            }
        }
        a.recycle();


    }

    private void setBgColor(int color) {
        this.setBackgroundColor(color);
    }

    public void setLabel(String s){
        ci_label.setText(s);
    }

    public void setMod(String s){
        ci_mod.setText(s);
    }

    public void setImg(Drawable drawable){
        ci_img.setImageDrawable(drawable);
    }

    public void setLabelFontSize(float size){
        ci_label.setTextSize(size);
    }

    public void setModFontSize(float size){
        ci_mod.setTextSize(size);
    }

    public void setLabelColor(int color){
        ci_label.setTextColor(color);
    }

    public void setModColor(int color){
        ci_mod.setTextColor(color);
    }

    private void setVisibility(View v, int visable){
        switch(visable){
            case 0:
                v.setVisibility(View.VISIBLE);
                break;
            case 1:
                v.setVisibility(View.INVISIBLE);
                break;
            case 2:
                v.setVisibility(View.GONE);
                break;
        }
    }
}
