package com.sardordev.nazorat.screens

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sardordev.nazorat.CalendarAdapter
import com.sardordev.nazorat.CalendarItem

import com.sardordev.nazorat.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var calendarAdapter: CalendarAdapter
    private val list = ArrayList<CalendarItem>()
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        for (day in 1..31) {
            list.add(CalendarItem(day, false))
        }

        calendarAdapter = CalendarAdapter(list)

        binding.rv.apply {
            adapter = calendarAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        calendarAdapter.selectToday()
        binding.rv.scrollToPosition(calendarAdapter.selectedPosition-3)




        return binding.root
    }
}