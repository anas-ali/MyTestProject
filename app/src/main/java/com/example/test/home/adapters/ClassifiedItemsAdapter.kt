package com.example.test.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.models.ClassifiedItem
import com.example.test.utils.getFormattedDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class ClassifiedItemsAdapter (val itemList : List<ClassifiedItem>) : RecyclerView.Adapter<ClassifiedItemsAdapter.ViewHolder>()  {

    private var onItemClickedAction: ((ClassifiedItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(inflatedView)
    }

    fun doOnItemClicked(action: (ClassifiedItem) -> Unit) {
        onItemClickedAction = action
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        val view = holder.itemView
        Picasso.with(view.context).load(item.imagesList[0]+".jpg")
        view.tvItemName.text = item.name
        view.tvTime.text = item.getFormattedDate()
        view.tvItemPrice.text = item.price
        view.setOnClickListener { onItemClickedAction?.invoke(item) }
    }

    class ViewHolder (v: View) : RecyclerView.ViewHolder(v)
}