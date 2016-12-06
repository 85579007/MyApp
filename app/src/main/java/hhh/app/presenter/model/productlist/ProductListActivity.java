package hhh.app.presenter.model.productlist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import hhh.app.R;
import hhh.app.presenter.ui.adapter.RvCommonAdapter;
import hhh.app.presenter.ui.adapter.RvViewHolder;
import hhh.app.presenter.ui.base.BaseActivity;
import hhh.app.data.bean.Product;
import hhh.app.presenter.utils.Code;
import hhh.app.presenter.ui.view.TitleView;

public class ProductListActivity extends BaseActivity implements ProductListContract.IProductListView {
    @BindView(R.id.title)
    TitleView title;
    @BindView(R.id.recyView)
    RecyclerView recyView;

    private RvCommonAdapter<Product> adapter;
    private List<Product> productsList;

    @Inject ProductListPresenter mPrensenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DaggerProductListComponent.builder()
//                .productListModule(new ProductListModule(this))
//                .build()
//                .inject(this);
        DaggerProductListComponent.builder().build().inject(this);

        productsList=new ArrayList<Product>();
        adapter=new RvCommonAdapter<Product>(ProductListActivity.this,productsList,R.layout.layout_list_item) {
            @Override
            public void convert(RvViewHolder holder, Product product) {
//                holder.getView(R.id.item_iv).set
                holder.setImageUrl(R.id.item_iv, Code.baseImageUrl+product.getImage())
                        .setRating(R.id.item_rating,product.getRating())
                        .setText(R.id.item_special,String.valueOf(product.getSpecial()))
                        .setText(R.id.item_price,String.valueOf(product.getPrice()))
                        .setText(R.id.item_label,product.getName());
            }
        };
        recyView.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
        recyView.setAdapter(adapter);
    }

//    @Override
//    protected void createPresenter() {
//        mPrensenter=new ProductListPresenter(ProductRepository.getInstance(RemoteIDataSource.getInstance(), LocalIDataSource.getInstance()));
//    }

    @Override
    protected void onStart() {
        super.onStart();
        mPrensenter.loadProducts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPrensenter.loadProducts();
    }

    @Override
    public void showProducts(List products) {
        productsList.clear();
        productsList.addAll(products);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNoProducts() {

    }

    @Override
    public void showLoading(boolean isShow) {
        if(isShow){
            showLoading();
        }else{
            dismissLoading();
        }
    }

}
