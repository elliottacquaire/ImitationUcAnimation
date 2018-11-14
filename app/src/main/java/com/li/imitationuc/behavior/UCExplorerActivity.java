package com.li.imitationuc.behavior;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import com.li.imitationuc.R;
import com.li.imitationuc.behavior.ucbehavior.UcNewsHeaderPagerBehavior;
import com.li.imitationuc.behavior.ucbehavior.helper.TestFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class UCExplorerActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, UcNewsHeaderPagerBehavior.OnPagerStateListener{

    private ViewPager mNewsPager;
    private TabLayout mTableLayout;
    private List<TestFragment> mFragments;
    private UcNewsHeaderPagerBehavior mPagerBehavior;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucexplorer);

        initView();
    }

    private void initView() {
        frameLayout = (FrameLayout)findViewById(R.id.id_uc_news_header_pager) ;
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) frameLayout.getLayoutParams();


        mPagerBehavior = (UcNewsHeaderPagerBehavior) layoutParams.getBehavior();
        if (mPagerBehavior != null){
            mPagerBehavior.setPagerStateListener(this);
        }

        mNewsPager = (ViewPager) findViewById(R.id.id_uc_news_content);
        mTableLayout = (TabLayout) findViewById(R.id.id_uc_news_tab);
        mFragments = new ArrayList<TestFragment>();
        for (int i = 0; i < 4; i++) {
            mFragments.add(TestFragment.newInstance(String.valueOf(i), false));
            mTableLayout.addTab(mTableLayout.newTab().setText("Tab" + i));
        }
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        mTableLayout.setOnTabSelectedListener(this);
        mNewsPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTableLayout));
        mNewsPager.setAdapter(new TestFragmentAdapter(mFragments, getSupportFragmentManager()));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mNewsPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPagerClosed() {
        Snackbar.make(mNewsPager, "pager closed", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPagerOpened() {
        Snackbar.make(mNewsPager, "pager opened", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (mPagerBehavior != null && mPagerBehavior.isClosed()) {
            mPagerBehavior.openPager();
        } else {
            super.onBackPressed();
        }
    }
}
