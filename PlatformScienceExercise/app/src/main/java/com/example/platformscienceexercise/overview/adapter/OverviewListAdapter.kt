package com.example.platformscienceexercise.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.platformscienceexercise.databinding.DriversListItemBinding
import com.example.platformscienceexercise.model.SelectedDriver

class OverviewListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<SelectedDriver, OverviewListAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: DriversListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(driver: SelectedDriver) {
            binding.selectedDriver = driver
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<SelectedDriver>() {
        override fun areItemsTheSame(oldItem: SelectedDriver, newItem: SelectedDriver): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SelectedDriver, newItem: SelectedDriver): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(DriversListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val driver = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(driver)
        }
        holder.bind(driver)
    }

    class OnClickListener(val clickListener: (driver: SelectedDriver) -> Unit) {
        fun onClick(driver: SelectedDriver) = clickListener(driver)
    }
}