package com.example.platformscienceexercise.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.platformscienceexercise.databinding.DriversListItemBinding

class OverviewListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<String, OverviewListAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: DriversListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(driver: String) {
            binding.driver = driver
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(DriversListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val driver= getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(driver)
        }
        holder.bind(driver)
    }

    class OnClickListener(val clickListener: (driver: String) -> Unit) {
        fun onClick(driver: String) = clickListener(driver)
    }
}