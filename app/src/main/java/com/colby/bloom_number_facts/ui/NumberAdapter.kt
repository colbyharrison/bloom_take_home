package com.colby.bloom_number_facts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.colby.bloom_number_facts.R
import com.colby.bloom_number_facts.data.NumberFact
import com.colby.bloom_number_facts.databinding.NumberFactCardBinding


class NumberAdapter :
    ListAdapter<NumberFact, NumberAdapter.ViewHolder>(DeviceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: NumberFactCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NumberFact) {
            binding.title.text = binding.root.context.getString(R.string.fact_title, item.number)
            binding.description.text = item.fact
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding =
                    NumberFactCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return ViewHolder(binding)

            }
        }
    }
}

class DeviceDiffCallback : DiffUtil.ItemCallback<NumberFact>() {
    override fun areItemsTheSame(oldItem: NumberFact, newItem: NumberFact): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(
        oldItem: NumberFact,
        newItem: NumberFact
    ): Boolean {
        return oldItem == newItem
    }
}