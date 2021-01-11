package com.example.test.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.models.ClassifiedItem

class GitHubRepoAdapter (val itemList : List<ClassifiedItem>) : RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepoAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: GitHubRepoAdapter.ViewHolder, position: Int) {
        //Picasso.with(holder.itemView.context).load(obj.avatar)
    }


    class ViewHolder (v: View) : RecyclerView.ViewHolder(v) {

    }


}