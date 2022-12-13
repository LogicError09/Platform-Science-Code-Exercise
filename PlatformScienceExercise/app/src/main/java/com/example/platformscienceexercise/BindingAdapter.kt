package com.example.platformscienceexercise

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.platformscienceexercise.model.SelectedDriver
import com.example.platformscienceexercise.overview.DataStatus
import com.example.platformscienceexercise.overview.adapter.OverviewListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SelectedDriver>?) {
    val adapter = recyclerView.adapter as OverviewListAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataStatus")
fun bindStatus(statusImageView: ImageView, status: DataStatus?) {
    when (status) {
        DataStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }
        DataStatus.LOADED -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}