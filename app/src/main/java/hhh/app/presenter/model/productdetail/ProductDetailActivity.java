package hhh.app.presenter.model.productdetail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hhh.app.R;
import hhh.app.data.bean.Product;
import hhh.app.presenter.ui.base.BaseActivity;
import hhh.app.presenter.ui.view.MyScrollView;
import hhh.app.presenter.ui.view.TitleView;

/**
 * Created by hhh on 2016/10/31.
 */
public class ProductDetailActivity extends BaseActivity implements ProductDetailContract.IProductDetailView,MyScrollView.OnScrollListener {
    @BindView(R.id.detail_title)
    TitleView detailTitle;

    @BindView(R.id.buy_btn)
    Button buyBtn;
    @BindView(R.id.detail_scrollview)
    MyScrollView detailScrollview;
    @BindView(R.id.parent_layout)
    LinearLayout parentLayout;
    @BindView(R.id.buy)
    LinearLayout buyLayout;
    @BindView(R.id.buy_top)
    LinearLayout buyTopLayout;
    @BindView(R.id.rollview)
    RollPagerView rollview;
    @BindView(R.id.buy_price)
    TextView buyPrice;
    @BindView(R.id.buy_oldprice)
    TextView buyOldprice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        init();
    }



    private void init() {
        rollview.setPlayDelay(1000);
        rollview.setAnimationDurtion(500);
        rollview.setAdapter(new RollAdapter());
        rollview.setHintView(new ColorPointHintView(this, Color.YELLOW,Color.WHITE));

        detailScrollview.setOnScrollListener(this);
        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(detailScrollview.getScrollY());
            }
        });
    }

    @OnClick(R.id.buy_btn)
    public void onClick() {
    }

    @Override
    public void onScroll(int scrollY) {
        int t = Math.max(scrollY, buyLayout.getTop());
        buyTopLayout.layout(0, t, buyTopLayout.getWidth(), t + buyTopLayout.getHeight());
    }

    @Override
    public void showProductDetail(Product p) {

    }

    @Override
    public void showLoading(boolean isShow) {

    }

    private class RollAdapter extends StaticPagerAdapter {
        private int[] imgs = {R.mipmap.yanjing7xq3, R.mipmap.yanjing91xq1, R.mipmap.yanjing91xq3, R.mipmap.yanjing10xq3};

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
