package com.example.assignment_test

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment_test.databinding.FragmentLinechart3Binding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class fragment3 : Fragment() {

    private lateinit var binding : FragmentLinechart3Binding
    private lateinit var line_chart: LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLinechart3Binding.inflate(layoutInflater)
        line_chart = binding.lineChart
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val linelist = ArrayList<Entry>()
        //placeholder replace it with database data
        for(i in 1 until 5) {
            val value = i * 10f
            val lineEntry = Entry(i.toFloat(),value)
            linelist.add(lineEntry)
        }

        val lineDateSet = LineDataSet(linelist, "Employees")
        val colors = ArrayList<Int>()

        for (i in 0 until linelist.size) {
            val randomColor = Color.rgb((0..255).random(), (0..255).random(), (0..255).random())
            colors.add(randomColor)
        }
        lineDateSet.colors = colors

        lineDateSet.setDrawValues(false)
        line_chart.data = LineData(lineDateSet)
        line_chart.animateY(500)

    }

}