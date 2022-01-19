package vp.ali.basisassignment.cardSwipeAdapte_Holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.CircularArray
import androidx.recyclerview.widget.RecyclerView
import vp.ali.basisassignment.modelClass.DataItem
import vp.ali.basisassignment.R

class SwipeAdapter(val context: Context,val list:CircularArray<DataItem>):RecyclerView.Adapter<SwipeHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item,parent,false)
        return SwipeHolder(view)
    }

    override fun onBindViewHolder(holder: SwipeHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size()
    }
}