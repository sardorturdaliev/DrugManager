package com.sardordev.nazorat

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sardordev.nazorat.databinding.ItemCalendarBinding
import java.util.*

class CalendarAdapter(val item: List<CalendarItem>) : RecyclerView.Adapter<CalendarAdapter.VH>() {
     var selectedPosition: Int = -1

    inner class VH(val binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(calendarItem: CalendarItem) {
            binding.textDay.text = calendarItem.day.toString()


            if (calendarItem.isSelected) {
                itemView.setBackgroundColor(itemView.resources.getColor(R.color.cardcolor,null))

            } else {
                itemView.background = null
            }


            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Update the selected state for the clicked item
                    item.forEach { it.isSelected = false }
                    item[position].isSelected = true
                    selectedPosition = position
                    notifyDataSetChanged()
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onbind(item[position])
        val calendar = Calendar.getInstance()
        val currentMonth =
            calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        item.forEach { it.day == currentDay }
    }

    fun selectToday() {
        val calendar = Calendar.getInstance()
        val currentMonth =
            calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        item.forEach { it.isSelected = it.day == currentDay }
        selectedPosition = item.indexOfFirst { it.day == currentDay }
        notifyDataSetChanged()
    }
}