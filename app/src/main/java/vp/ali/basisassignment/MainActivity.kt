package vp.ali.basisassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONException
import org.json.JSONObject
class MainActivity : AppCompatActivity() {
    var list=ArrayList<DataItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api=ApiCall()
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Ali",Thread.currentThread().name)
            api.getList()?.forEach {
                list.addAll(listOf(it))
            }
            printList(list)
            withContext(Dispatchers.Main)
            {
                cardSwipe.adapter=SwipeAdapter(this@MainActivity,list)
                val cardStackLayoutManager=CardStackLayoutManager(this@MainActivity, CardStackListener.DEFAULT)
                cardStackLayoutManager.setVisibleCount(3)
                cardSwipe.layoutManager=cardStackLayoutManager
            }
        }

    }
    fun printList(list:ArrayList<DataItem>)
    {
        list.forEach {
            Log.d("List","${it.text} ")
        }
    }
}