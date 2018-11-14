package com.li.imitationuc.behavior;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.li.imitationuc.R;
import com.li.imitationuc.behavior.adapter.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class JDPullAndPushActivity extends AppCompatActivity implements OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {


    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView recyclerView;

    private QuickAdapter quickAdapter;

    private List<RecyclerBean> recyclerBeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdpull_and_push);

        initDate();
        initView();

    }

    private void initDate() {
        if (recyclerBeanList == null || recyclerBeanList.size() <= 0){
            recyclerBeanList = new ArrayList<>();
            for (int i = 0 ; i < 10 ; i++){
                RecyclerBean recyclerBean = new RecyclerBean();
                recyclerBean.setImgUrl("httssasfasfs" + i);
                recyclerBean.setTitle("title"+i);
                recyclerBeanList.add(recyclerBean);
            }
        }
    }

    private void initView() {
        swipeToLoadLayout = (SwipeToLoadLayout)findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setRefreshing(true);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setInitialPrefetchItemCount(6);


        quickAdapter = new QuickAdapter(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(quickAdapter);
        quickAdapter.setNewData(recyclerBeanList);
        quickAdapter.setEnableLoadMore(false);
        quickAdapter.setOnLoadMoreListener(this,recyclerView);
//        quickAdapter.notifyDataSetChanged();

        quickAdapter.loadMoreEnd();

    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
                if (recyclerBeanList != null){
                    for (int i = 0 ; i < 5 ; i++){
                        RecyclerBean recyclerBean = new RecyclerBean();
                        recyclerBean.setImgUrl("refresh on id " + i);
                        recyclerBean.setTitle("resfree"+i);
                        recyclerBeanList.add(i,recyclerBean);
                    }
                }
                quickAdapter.setNewData(recyclerBeanList);
                quickAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    @Override
    public void onLoadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (recyclerBeanList != null){
                    for (int i = 0 ; i < 7 ; i++){
                        RecyclerBean recyclerBean = new RecyclerBean();
                        recyclerBean.setImgUrl("loadmore on id " + i);
                        recyclerBean.setTitle("loadmore"+i);
                        recyclerBeanList.add(recyclerBean);
                    }
                }
                quickAdapter.setNewData(recyclerBeanList);
                quickAdapter.notifyDataSetChanged();
                quickAdapter.loadMoreComplete();
            }
        },2000);
    }


}
