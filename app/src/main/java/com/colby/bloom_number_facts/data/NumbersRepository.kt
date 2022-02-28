package com.colby.bloom_number_facts.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NumbersRepository(
    private val dataSource: NumbersDataSource = NumbersDataSource.create(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getNumbers(range: String) = withContext(defaultDispatcher) {
        dataSource.getNumbers(range).map {
            NumberFact(it.key, it.value)
        }
    }
}