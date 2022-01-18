package vp.ali.basisassignment

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiCall {
    private val list=ArrayList<DataItem>()

    fun getList(): ArrayList<DataItem>? {
        var urlConnection: HttpURLConnection? = null
        try {
            val url = URL("https://gist.githubusercontent.com/anishbajpai014/d482191cb4fff429333c5ec64b38c197/raw/b11f56c3177a9ddc6649288c80a004e7df41e3b9/HiringTask.json")
            urlConnection = url.openConnection() as HttpURLConnection
            val inputStream = urlConnection.inputStream
            val inputStreamReader = InputStreamReader(inputStream)
            var data = inputStreamReader.read()
            val stringBuffer = StringBuffer()
            while (data != -1) {
                val responseChar = data.toChar()
                stringBuffer.append(responseChar)
                data = inputStreamReader.read()
            }
            buildResponseModel(stringBuffer)
            return list
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            urlConnection?.disconnect()
        }
    }
    private fun buildResponseModel(stringBuffer: StringBuffer) {
        stringBuffer.deleteCharAt(0)
        try {
            val jsonObject = JSONObject(stringBuffer.toString())
            val array=jsonObject.getJSONArray("data")
            for (i in 0 until array.length()) {
                val jsonObject1= JSONObject(array[i].toString())
                val id=jsonObject1.get("id")
                val text=jsonObject1.get("text")
                list.add(DataItem(id.toString(),text.toString()))
            }
        } catch (e: JSONException) {
            Log.d("Hello","$e")
        }
    }
}