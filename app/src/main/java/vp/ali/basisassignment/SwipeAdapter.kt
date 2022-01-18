package vp.ali.basisassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SwipeAdapter(val context: Context,val list:ArrayList<DataItem>):RecyclerView.Adapter<SwipeHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return SwipeHolder(view)
    }

    override fun onBindViewHolder(holder: SwipeHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}