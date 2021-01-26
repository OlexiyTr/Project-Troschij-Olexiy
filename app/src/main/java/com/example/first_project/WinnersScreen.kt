package com.example.first_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_project.databinding.WinnersSreenLayoutBinding

class WinnersScreen : AppCompatActivity() {
    companion object {
        var adapter = WinnerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBinding()
        setupListeners()
        setupAdapter()
    }

    private lateinit var binding: WinnersSreenLayoutBinding

    private fun setupBinding() {
        binding = WinnersSreenLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnClearList.setOnClickListener {
            clearList()
        }
    }

    private fun setupAdapter() {
        val recyclerView: RecyclerView = binding.listOfWinners
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun clearList() {
        val recyclerView: RecyclerView = binding.listOfWinners
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.winners.clear()
        recyclerView.adapter?.notifyDataSetChanged()
    }
}