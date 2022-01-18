package vp.ali.basisassignment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class SwipeHolder(val view:View): RecyclerView.ViewHolder(view) {
    fun setData(dataItem: DataItem)
    {
        view.tvText.text=dataItem.text
    }
}