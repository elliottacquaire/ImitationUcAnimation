package com.li.imitationuc.behavior;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.li.imitationuc.R;
import com.li.imitationuc.behavior.adapter.RecyclerViewAdapter;

public class UpDownBehaviorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_down_behavior);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        initView();

    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setInitialPrefetchItemCount(6);

        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
