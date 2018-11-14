package com.li.imitationuc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.li.imitationuc.behavior.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
//            R.id.button -> showToast("${button.text}")

            R.id.button -> jumpActivity(ImitateUCActivity().javaClass)
            R.id.button2 -> jumpActivity(FloatingActionButtionActivity().javaClass)
            R.id.button3 -> jumpActivity(JDPullAndPushActivity().javaClass)
            R.id.button4 -> jumpActivity(SampleHeadBehavorActivity().javaClass)
            R.id.button5 -> jumpActivity(TestBehaviorActivity().javaClass)
            R.id.button6 -> jumpActivity(UpDownBehaviorActivity().javaClass)
            R.id.button7 -> jumpActivity(SmartRefreshLayoutActivity().javaClass)
            R.id.button8 -> jumpActivity(UCExplorerActivity().javaClass)
            R.id.button9 -> jumpActivity(RecyclerViewActivity().javaClass)
        }
    }

    fun showToast(str : String){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show()
    }

    fun jumpActivity(cls : Class<Any>){
        val intent = Intent();
        intent.setClass(this,cls)
//        intent.setClass(this,cls.javaClass)
//        intent.setClass(this,ImitateUCActivity::class.java)
        startActivity(intent)
    }
}
