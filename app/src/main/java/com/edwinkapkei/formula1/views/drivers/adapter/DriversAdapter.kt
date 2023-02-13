package com.edwinkapkei.formula1.views.drivers.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.data.model.driver.DriverAndImage
import com.edwinkapkei.formula1.databinding.ListItemDriverBinding
import java.util.*

class DriversAdapter : RecyclerView.Adapter<DriversAdapter.DriversViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<DriverAndImage>() {
        override fun areContentsTheSame(
            oldItem: DriverAndImage,
            newItem: DriverAndImage
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: DriverAndImage,
            newItem: DriverAndImage
        ): Boolean {
            return oldItem.driverStanding.driver.driverId == newItem.driverStanding.driver.driverId
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversViewHolder {
        val binding =
            ListItemDriverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DriversViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DriversAdapter.DriversViewHolder, position: Int) {
        val driver = differ.currentList[position]

        holder.binding.position.text = driver.driverStanding.position
        holder.binding.name.text =
            "${driver.driverStanding.driver.givenName} ${driver.driverStanding.driver.familyName}"
        holder.binding.points.text =
            holder.binding.points.context.getString(R.string.points, driver.driverStanding.points)

        Glide
            .with(holder.binding.root.context)
            .load(driver.driverImageUrl)
            .placeholder(R.drawable.racing_helmet_blue)
            .into(holder.binding.avatar);
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class DriversViewHolder(val binding: ListItemDriverBinding) :
        RecyclerView.ViewHolder(binding.root)
}