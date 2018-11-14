package com.li.imitationuc.behavior.smartrefresh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.li.imitationuc.R;
import com.li.imitationuc.behavior.RecyclerBean;
import com.li.imitationuc.behavior.adapter.QuickAdapter;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class TestSmartRefreshActivity extends AppCompatActivity {

    private RefreshLayout refreshLayout;

    private RecyclerView recyclerView;

    private QuickAdapter quickAdapter;

    private List<RecyclerBean> recyclerBeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_smart_refresh);

        initDate();
        initView();

    }


    private void initView() {
        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

//        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true)); //为 贝塞尔雷达 样式  header
//        refreshLayout.setRefreshHeader(new WaterDropHeader(this)); //苹果水滴效果
//        refreshLayout.setRefreshHeader(new StoreHouseHeader(this)); //store house
//        refreshLayout.setRefreshHeader(new WaveSwipeHeader(this)); //全屏水波
//        refreshLayout.setRefreshHeader(new MaterialHeader(this)); //官方主题
//        refreshLayout.setRefreshHeader(new FunGameHitBlockHeader(this)); //打砖块
//        refreshLayout.setRefreshHeader(new FunGameBattleCityHeader(this)); //城市战争
//        refreshLayout.setRefreshHeader(new TaurusHeader(this)); //冲上云霄
//        refreshLayout.setRefreshHeader(new PhoenixHeader(this)); //金色校园
//        refreshLayout.setRefreshHeader(new ClassicsHeader(this)); //传统风格
//        refreshLayout.setRefreshHeader(new FlyRefreshHeader(this)); //飞机飞飞
//        refreshLayout.setRefreshHeader(new BezierRadarHeader(this)); //镭射信号
//        refreshLayout.setRefreshHeader(new BezierCircleHeader(this)); //弹出圈圈
//        refreshLayout.setRefreshHeader(new DropBoxHeader(this)); //掉盒子
        refreshLayout.setRefreshHeader(new DeliveryHeader(this)); //气球快递


        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale)); //球脉冲 样式 footer
        refreshLayout.setRefreshFooter(new FalsifyFooter(this));


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

                refreshLayout.finishRefresh(7000);
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
//        quickAdapter.setOnLoadMoreListener(this,recyclerView);
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

}
