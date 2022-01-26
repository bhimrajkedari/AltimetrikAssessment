package com.bhimraj.altimetrikassessment.adapters

import com.bhimraj.altimetrikassessment.models.LaunchesItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bhimraj.altimetrikassessment.R
import com.bhimraj.altimetrikassessment.databinding.LaunchRowItemBinding
import kotlinx.android.extensions.LayoutContainer

class LaunchesListAdapter : RecyclerView.Adapter<LaunchesListAdapter.ViewHolder>() {

    private var mList: List<LaunchesItem>? = listOf()

    fun setData(list: List<LaunchesItem>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: LaunchRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.launch_row_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.launch = mList?.get(position)
        holder.itemBinding.links = mList?.get(position)?.links
    }

    class ViewHolder(var itemBinding: LaunchRowItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root), LayoutContainer {
        override val containerView: View?
            get() = itemBinding.root
    }
}