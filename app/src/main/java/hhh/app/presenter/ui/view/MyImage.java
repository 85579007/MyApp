package hhh.app.presenter.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.utils.ImageUtils;

import hhh.app.R;

/**
 * Created by hhh on 2016/11/10.
 */
public class MyImage extends View {
    int rate=1;//缩放因子，默认安装96px设计，若设置48，rate0.5

    Rect bgRect;//背景绘制区域,圆，矩形，圆角矩形
    Rect imgRect;//src图片绘制区域，src属性
    Rect tipRect;//提示框区域
    Rect txtBound=new Rect();

    String tipTxt;

    final int PADDING =8;
    final int WIDTH =96;
    final int TIPFONTSIZE =30;

    int bgColor;
    int tipTxtColor;
    int tipBgColor;
    Drawable srcImg;

    Paint paint;
//    Paint bgPaint;
//    Paint tipPaint;

    public MyImage(Context context) {
        super(context);
        init(null);
    }

    public MyImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.MyImage);
        int N=a.getIndexCount();
        for(int i=0;i<N;i++){
            int index=a.getIndex(i);
            switch (index){
                case R.styleable.MyImage_rating:
                    rate=a.getInteger(index,1);
                    break;
                case R.styleable.MyImage_bg_color:
                    bgColor=a.getColor(index,Color.WHITE);
                    break;
                case R.styleable.MyImage_img:
                    srcImg=a.getDrawable(index);
                    break;
                case R.styleable.MyImage_tip_txt:
                    setTipText(a.getString(index));
                    break;
                case R.styleable.MyImage_tip_txt_color:
                    setTipTxtColor(a.getColor(index,Color.BLACK));
                    break;
            }
        }
        a.recycle();

        int p= PADDING /rate;
        int r= WIDTH-PADDING;
        bgRect=new Rect(p,p,r,r);
        int p2=WIDTH/4;
        int r2=WIDTH-p2;
        imgRect=new Rect(p2,p2,r2,r2);
        int p3=WIDTH-PADDING/2-WIDTH/3;
        int r3=WIDTH-PADDING/2;
        tipRect=new Rect(p3,PADDING/2,r3,WIDTH/3);

        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void setTipTxtColor(int color) {
        tipTxtColor=color;
        invalidate();
    }

    private void setTipText(String string) {
        tipTxt=string;
        invalidate();
    }

    private void setBgColor(int color) {
        bgColor=color;
        invalidate();
    }

    public void setRate(int r){
        rate=r;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(WIDTH,WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(bgColor);
        canvas.drawCircle(WIDTH/2,WIDTH/2,bgRect.width()/2,paint);

        canvas.drawBitmap(ImageUtils.drawable2Bitmap(srcImg),null,imgRect,paint);

        paint.setStrokeWidth(3);
        paint.setTextSize(TIPFONTSIZE/rate);
        paint.setColor(Color.RED);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        // 转载请注明出处：http://blog.csdn.net/hursing
        int baseline = (tipRect.bottom + tipRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(tipTxt, tipRect.centerX(), baseline, paint);

//        paint.getTextBounds(tipTxt,0,tipTxt.length(),txtBound);
//        float xpos=tipRect.left+(tipRect.width()-txtBound.width())/2;
//        float ybase=tipRect.height()+txtBound.height()+(tipRect.height()-txtBound.height())/2;
//
//        paint.setColor(tipTxtColor);
//        canvas.drawText(tipTxt,xpos,ybase,paint);
    }
}
