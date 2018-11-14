package com.li.imitationuc.behavior.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.li.imitationuc.R;
import com.li.imitationuc.behavior.RecyclerBean;

import java.util.List;

public class QuickAdapter extends BaseQuickAdapter<RecyclerBean,BaseViewHolder> {

    private Context context;

    public QuickAdapter(Context context) {
        super(R.layout.item_recycler,null);
        this.context = context;
    }

    public QuickAdapter(int layoutResId, @Nullable List<RecyclerBean> data) {
        super(layoutResId, data);
    }

    public QuickAdapter(@Nullable List<RecyclerBean> data) {
        super(data);
    }

    public QuickAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecyclerBean item) {
        helper.setText(R.id.tv_title,item.getTitle());

        //imageView
        int position = helper.getLayoutPosition();
        if (position % 2 == 0){
            helper.setImageResource(R.id.img_head,R.mipmap.biaoqingbao_blue);
        }else {
//            Glide.with(context).load("https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300")
//                    .into((ImageView) helper.getView(R.id.img_head));
        }
        helper.addOnClickListener(R.id.img_head); //添加点击事件

//        GlideApp.
    }
}
