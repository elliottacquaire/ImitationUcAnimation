package com.li.imitationuc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import com.li.imitationuc.ItemDecoration.GalleryItemDecoration;
import com.li.imitationuc.behavior.RecyclerBean;
import com.li.imitationuc.behavior.adapter.HorizoRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private HorizoRecyclerAdapter recyclerViewAdapter;

    private List<RecyclerBean> recyclerBeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView1 = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView2 = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView3 = (RecyclerView)findViewById(R.id.recyclerView3);

        initDate();

        initView();
    }


    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setInitialPrefetchItemCount(6);

        recyclerViewAdapter = new HorizoRecyclerAdapter(this);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setNewData(recyclerBeanList);
//        recyclerViewAdapter.notifyDataSetChanged();


        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setNewData(recyclerBeanList);
//        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        PagerSnapHelper linearSnapHelper = new PagerSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView2);


        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this);
        linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        recyclerView3.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setNewData(recyclerBeanList);
        recyclerView3.addItemDecoration(new GalleryItemDecoration(this));
    }



    private void initDate() {
        if (recyclerBeanList == null || recyclerBeanList.size() <= 0){
            recyclerBeanList = new ArrayList<>();
            for (int i = 0 ; i < 15 ; i++){
                RecyclerBean recyclerBean = new RecyclerBean();
                recyclerBean.setImgUrl("httssasfasfs" + i);
                recyclerBean.setTitle("title"+i);
                recyclerBeanList.add(recyclerBean);
            }
        }
    }

}
