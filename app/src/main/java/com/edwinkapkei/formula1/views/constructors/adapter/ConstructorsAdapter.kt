package com.edwinkapkei.formula1.views.constructors.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.data.model.constructor.ConstructorStanding
import com.edwinkapkei.formula1.data.model.driver.DriverStanding
import com.edwinkapkei.formula1.data.model.schedule.Race
import com.edwinkapkei.formula1.databinding.ListItemConstructorBinding
import com.edwinkapkei.formula1.databinding.ListItemDriverBinding
import com.edwinkapkei.formula1.databinding.ListItemScheduleBinding
import com.edwinkapkei.formula1.views.drivers.adapter.DriversAdapter
import java.util.*

class ConstructorsAdapter : RecyclerView.Adapter<ConstructorsAdapter.ConstructorsViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<ConstructorStanding>() {
        override fun areContentsTheSame(
            oldItem: ConstructorStanding,
            newItem: ConstructorStanding
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: ConstructorStanding,
            newItem: ConstructorStanding
        ): Boolean {
            return oldItem.constructor.constructorId == newItem.constructor.constructorId
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConstructorsAdapter.ConstructorsViewHolder {
        val binding =
            ListItemConstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConstructorsViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ConstructorsAdapter.ConstructorsViewHolder,
        position: Int
    ) {
        val constructor = differ.currentList[position]

        holder.binding.position.text = constructor.position
        holder.binding.name.text = constructor.constructor.name
        holder.binding.points.text =
            holder.binding.points.context.getString(R.string.points, constructor.points)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ConstructorsViewHolder(val binding: ListItemConstructorBinding) :
        RecyclerView.ViewHolder(binding.root)
}