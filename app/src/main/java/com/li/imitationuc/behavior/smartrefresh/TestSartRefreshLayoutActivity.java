package com.li.imitationuc.behavior.smartrefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.li.imitationuc.R;
import com.li.imitationuc.behavior.RecyclerBean;
import com.li.imitationuc.behavior.adapter.QuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class TestSartRefreshLayoutActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    private RefreshLayout refreshLayout;

    private RecyclerView recyclerView;

    private QuickAdapter quickAdapter;

    private List<RecyclerBean> recyclerBeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sart_refresh_layout);

        initDate();
        initView();
    }

    private void initView() {
        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
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

                refreshLayout.finishRefresh(2000);
            }
        });


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
