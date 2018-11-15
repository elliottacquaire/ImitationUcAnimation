package com.li.imitationuc.behavior

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.li.imitationuc.R
import com.li.imitationuc.behavior.smartrefresh.TestSartRefreshLayoutActivity
import com.li.imitationuc.behavior.smartrefresh.TestSmartRefreshActivity
import kotlinx.android.synthetic.main.activity_smart_refresh_layout.*

class SmartRefreshLayoutActivity : AppCompatActivity() , View.OnClickListener{

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button10 -> jumpActivity(TestSartRefreshLayoutActivity().javaClass)
            R.id.button11 -> jumpActivity(TestSmartRefreshActivity().javaClass)
            R.id.button12 -> jumpActivity(CustomerSwapRefreshActivity().javaClass)
        }
    }

    fun jumpActivity(cls : Class<Any>){
        val intent = Intent()
        intent.setClass(this,cls)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_refresh_layout)

        button10.setOnClickListener(this)
        button11.setOnClickListener(this)
        button12.setOnClickListener(this)
    }
}
