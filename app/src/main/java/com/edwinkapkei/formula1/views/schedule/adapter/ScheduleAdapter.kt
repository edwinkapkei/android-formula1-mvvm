package com.edwinkapkei.formula1.views.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edwinkapkei.formula1.data.model.schedule.Race
import com.edwinkapkei.formula1.databinding.ListItemScheduleBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    private val callback =
        object : DiffUtil.ItemCallback<Race>() {
            override fun areContentsTheSame(
                oldItem: Race,
                newItem: Race,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: Race,
                newItem: Race,
            ): Boolean {
                return oldItem.raceName == newItem.raceName
            }
        }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScheduleViewHolder {
        val binding = ListItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ScheduleViewHolder,
        position: Int,
    ) {
        val race = differ.currentList[position]

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatter.parse(race.date)
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
        date?.let {
            val day = dayFormat.format(it)
            val month = monthFormat.format(it)
            holder.binding.day.text = day.lowercase()
            holder.binding.month.text = month.lowercase()
        }

        holder.binding.country.text = race.circuit.location.country
        holder.binding.raceName.text = race.raceName
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ScheduleViewHolder(val binding: ListItemScheduleBinding) : RecyclerView.ViewHolder(binding.root)
}
