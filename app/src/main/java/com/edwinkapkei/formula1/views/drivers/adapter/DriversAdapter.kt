package com.edwinkapkei.formula1.views.drivers.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.data.model.driver.DriverStanding
import com.edwinkapkei.formula1.data.model.schedule.Race
import com.edwinkapkei.formula1.databinding.ListItemDriverBinding
import com.edwinkapkei.formula1.databinding.ListItemScheduleBinding
import java.util.*

class DriversAdapter : RecyclerView.Adapter<DriversAdapter.DriversViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<DriverStanding>() {
        override fun areContentsTheSame(oldItem: DriverStanding, newItem: DriverStanding): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: DriverStanding, newItem: DriverStanding): Boolean {
            return oldItem.driver.driverId == newItem.driver.driverId
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

        holder.binding.position.text = driver.position
        holder.binding.name.text = "${driver.driver.givenName} ${driver.driver.familyName}"
        holder.binding.points.text =
            holder.binding.points.context.getString(R.string.points, driver.points)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class DriversViewHolder(val binding: ListItemDriverBinding) :
        RecyclerView.ViewHolder(binding.root)
}