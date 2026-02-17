package com.edwinkapkei.formula1.views.constructors.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.data.model.constructor.ConstructorAndTeamCarImage
import com.edwinkapkei.formula1.databinding.ListItemConstructorBinding

class ConstructorsAdapter : RecyclerView.Adapter<ConstructorsAdapter.ConstructorsViewHolder>() {
    private val callback =
        object : DiffUtil.ItemCallback<ConstructorAndTeamCarImage>() {
            override fun areContentsTheSame(
                oldItem: ConstructorAndTeamCarImage,
                newItem: ConstructorAndTeamCarImage,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: ConstructorAndTeamCarImage,
                newItem: ConstructorAndTeamCarImage,
            ): Boolean {
                return oldItem.constructorStanding.constructor.constructorId ==
                    newItem
                        .constructorStanding.constructor.constructorId
            }
        }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ConstructorsAdapter.ConstructorsViewHolder {
        val binding =
            ListItemConstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConstructorsViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ConstructorsAdapter.ConstructorsViewHolder,
        position: Int,
    ) {
        val constructor = differ.currentList[position]

        holder.binding.position.text = constructor.constructorStanding.position
        holder.binding.name.text = constructor.constructorStanding.constructor.name
        holder.binding.points.text =
            holder.binding.points.context.getString(R.string.points, constructor.constructorStanding.points)

        Glide
            .with(holder.binding.root.context)
            .load(constructor.teamCarImageUrl)
            .placeholder(R.drawable.steering_blue)
            .into(holder.binding.avatar)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ConstructorsViewHolder(val binding: ListItemConstructorBinding) :
        RecyclerView.ViewHolder(binding.root)
}
