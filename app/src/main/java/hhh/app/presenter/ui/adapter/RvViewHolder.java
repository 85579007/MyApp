package hhh.app.presenter.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by hhh on 2016/10/11.
 */
public class RvViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private Context mContext;
    private View mConverView;

    public static interface IRvHolderClick{
        public void onHolderClick(View v);
    }

    public RvViewHolder(Context context,View itemView) {
        super(itemView);
        mConverView=itemView;
        mContext=context;
        mViews=new SparseArray<View>();
    }

    public static RvViewHolder get(Context context, ViewGroup parent,int layoutId){
        View item= LayoutInflater.from(context).inflate(layoutId,parent,false);
        return new RvViewHolder(context,item);
    }

    public <T extends View> T getView(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mConverView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public RvViewHolder setImageResouce(int viewId,int resId){
        ImageView view=getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public RvViewHolder setImageDrawable(int viewId, Drawable drawable){
        ImageView view=getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public RvViewHolder setImageUrl(int viewId,String url){
        ImageView view=getView(viewId);
        Picasso.with(mContext)
                .load(url)
                .into(view);
        return this;
    }

    public RvViewHolder setText(int viewId,String txt){
        TextView view=getView(viewId);
        view.setText(txt);
        return this;
    }

    public RvViewHolder setRating(int viewId,double rating){
        RatingBar view=getView(viewId);
        view.setRating((float)rating);
        return this;
    }

    public void setViewOnClick(int viewId,View.OnClickListener listener){
        if(listener!=null){
            getView(viewId).setOnClickListener(listener);
        }
    }
}
