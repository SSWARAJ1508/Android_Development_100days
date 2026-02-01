package com.example.day_07_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            ItemModel("RecyclerView", "Displays large sets of data efficiently"),
            ItemModel("ViewHolder", "Holds view references"),
            ItemModel("Adapter", "Binds data to views"),
            ItemModel("LayoutManager", "Controls item layout"),
            ItemModel("Performance", "Recycles views instead of recreating")
        )

        recyclerView.adapter = ItemAdapter(items)
    }
}
