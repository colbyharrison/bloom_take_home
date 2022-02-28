package com.colby.bloom_number_facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.colby.bloom_number_facts.data.NumbersDataSource

class NumberCollectorViewModel(private val dataSource: NumbersDataSource = NumbersDataSource.create()) :
    ViewModel() {

    val numbers: LiveData<Map<String,String>> = liveData {
        emit(dataSource.getNumbers("1,2"))
    }

}