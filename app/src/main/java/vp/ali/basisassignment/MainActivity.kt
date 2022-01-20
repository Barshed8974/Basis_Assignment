package vp.ali.basisassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
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
    private var list=ArrayList<DataItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api= ApiCall()
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Ali",Thread.currentThread().name)
            api.getList()?.forEach {
                list.add(it)
            }
            list.add(DataItem("",""))
            withContext(Dispatchers.Main)
            {
                cardSwipe.adapter= SwipeAdapter(this@MainActivity,list)
                progress_circular.max=list.size-1
            }
        }

        //
        cardSwipe.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("poss",(position+1).toString())
                if (position==list.size-1)
                    cardSwipe.adapter= SwipeAdapter(this@MainActivity,list)
                if (position<list.size-1)
                text.text=(position+1).toString()
                progress_circular.progress=position+1
            }
        })

        //observing progress
        progress_circular.setOnClickListener {
            if (list.size>0)
                cardSwipe.adapter= SwipeAdapter(this@MainActivity,list)
        }
    }
}