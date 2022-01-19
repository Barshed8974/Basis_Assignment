package vp.ali.basisassignment.cardSwipeAdapte_Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import vp.ali.basisassignment.modelClass.DataItem

class SwipeHolder(val view:View): RecyclerView.ViewHolder(view) {
    fun setData(dataItem: DataItem)
    {
        view.tvText.text=dataItem.text
    }
}