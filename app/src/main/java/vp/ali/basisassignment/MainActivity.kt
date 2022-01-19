package vp.ali.basisassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.collection.CircularArray
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import vp.ali.basisassignment.api_Call.ApiCall
import vp.ali.basisassignment.cardSwipeAdapte_Holder.SwipeAdapter
import vp.ali.basisassignment.modelClass.DataItem


class MainActivity : AppCompatActivity() {
    private var list=CircularArray<DataItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api= ApiCall()
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Ali",Thread.currentThread().name)
            api.getList()?.forEach {
                list.addLast(it)
            }
            withContext(Dispatchers.Main)
            {
                cardSwipe.adapter= SwipeAdapter(this@MainActivity,list)
                progress_circular.max=list.size()
            }
        }
        cardSwipe.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                progress_circular.progress=position+1
                Log.d("poss",(position+1).toString())
                text.text=(position+1).toString()
            }
        })
        progress_circular.setOnClickListener {
            if (list.size()>0)
                cardSwipe.adapter= SwipeAdapter(this@MainActivity,list)
        }
    }
}