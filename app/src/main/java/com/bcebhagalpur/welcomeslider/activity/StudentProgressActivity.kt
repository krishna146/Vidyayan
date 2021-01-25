package com.bcebhagalpur.welcomeslider.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bcebhagalpur.welcomeslider.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate


class StudentProgressActivity : AppCompatActivity() {

    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_progress)

         pieChart = findViewById<PieChart>(R.id.pieChart)

        val pieEntry = ArrayList<PieEntry>()
        pieEntry.add(PieEntry(900f, 0f))
        pieEntry.add(PieEntry(1000f, 1f))
        pieEntry.add(PieEntry(1150f, 2f))
        pieEntry.add(PieEntry(1340f, 3f))
        pieEntry.add(PieEntry(1469f, 4f))
        pieEntry.add(PieEntry(1587f, 5f))
        pieEntry.add(PieEntry(1591f, 6f))
        pieEntry.add(PieEntry(1695f, 7f))
        pieEntry.add(PieEntry(1878f, 8f))
        pieEntry.add(PieEntry(1975f, 9f))
        val dataSet = PieDataSet(pieEntry, "Number Of Students")
        val year = ArrayList<Any>()
        year.add("2008")
        year.add("2009")
        year.add("2010")
        year.add("2011")
        year.add("2012")
        year.add("2013")
        year.add("2014")
        year.add("2015")
        year.add("2016")
        year.add("2017")
       val data:PieData= PieData(dataSet)
        pieChart.data = data
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        pieChart.animateXY(5000, 5000)

        val chart = findViewById<BarChart>(R.id.barChart)
        val NoOfEmp = ArrayList<BarEntry>()
        NoOfEmp.add(BarEntry(945f, 0f))
        NoOfEmp.add(BarEntry(1040f, 1f))
        NoOfEmp.add(BarEntry(1133f, 2f))
        NoOfEmp.add(BarEntry(1240f, 3f))
        NoOfEmp.add(BarEntry(1369f, 4f))
        NoOfEmp.add(BarEntry(1487f, 5f))
        NoOfEmp.add(BarEntry(1501f, 6f))
        NoOfEmp.add(BarEntry(1645f, 7f))
        NoOfEmp.add(BarEntry(1578f, 8f))
        NoOfEmp.add(BarEntry(1695f, 9f))

//        val year = ArrayList<Any>()
//
//        year.add("2008")
//        year.add("2009")
//        year.add("2010")
//        year.add("2011")
//        year.add("2012")
//        year.add("2013")
//        year.add("2014")
//        year.add("2015")
//        year.add("2016")
//        year.add("2017")
        val bardataset = BarDataSet(NoOfEmp, "No Of Students")
        chart.animateY(5000)
        val data1 = BarData(bardataset)
        bardataset.setColors(*ColorTemplate.COLORFUL_COLORS)
        chart.data = data1
    }
}

