package com.li.imitationuc.behavior.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.li.imitationuc.R;
import com.li.imitationuc.behavior.RecyclerBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder>{

    private Context context;

    private List<RecyclerBean> recyclerBeanList;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        initData();
    }

    private void initData() {
        if (recyclerBeanList == null || recyclerBeanList.size() <= 0){
            recyclerBeanList = new ArrayList<>();
            for (int i = 0 ; i < 22 ; i++){
                RecyclerBean recyclerBean = new RecyclerBean();
                recyclerBean.setImgUrl("httssasfasfs" + i);
                recyclerBean.setTitle("title"+i);
                recyclerBeanList.add(recyclerBean);
            }
        }
    }

    public RecyclerViewAdapter(Context context, List<RecyclerBean> recyclerBeanList) {
        this.context = context;
        this.recyclerBeanList = recyclerBeanList;
        initData();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        RecyclerBean recyclerBean = recyclerBeanList.get(i);
        holder.textView.setText(recyclerBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return recyclerBeanList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.img_head);
            textView = (TextView)itemView.findViewById(R.id.tv_title);
        }
    }
}
