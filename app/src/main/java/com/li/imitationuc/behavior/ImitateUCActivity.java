package com.li.imitationuc.behavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.li.imitationuc.R;

public class ImitateUCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitate_uc);

//        Button click = (Button)findViewById(R.id.btn);
//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ImitateUCActivity.this,TestBehaviorActivity.class));
//            }
//        });
    }

    public void titleMode(View v){
        findViewById(R.id.iv_avatar).setVisibility(View.INVISIBLE);
        findViewById(R.id.tv_title).setVisibility(View.VISIBLE);
//        startActivity(new Intent(ImitateUCActivity.this,FloatingActionButtionActivity.class));

//        startActivity(new Intent(ImitateUCActivity.this,SampleHeadBehavorActivity.class));

        startActivity(new Intent(ImitateUCActivity.this,JDPullAndPushActivity.class));

    }

    public void avatarMode(View v ){

        findViewById(R.id.iv_avatar).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_title).setVisibility(View.INVISIBLE);

        startActivity(new Intent(ImitateUCActivity.this,UpDownBehaviorActivity.class));
    }
}
