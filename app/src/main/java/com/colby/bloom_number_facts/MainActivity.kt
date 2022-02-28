package com.colby.bloom_number_facts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NumberCollectorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NumberCollectorViewModel::class.java]


        viewModel.numbers.observe(this){
            Log.i("main", it.get("2").toString())
        }

        setContentView(R.layout.activity_main)
    }
}