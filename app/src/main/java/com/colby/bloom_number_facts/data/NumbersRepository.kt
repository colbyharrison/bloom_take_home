package com.colby.bloom_number_facts.data

import com.colby.bloom_number_facts.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class NumbersRepository(
    private val dataSource: NumbersDataSource = NumbersDataSource.create(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getNumbers(range: String) = withContext(defaultDispatcher) {
        try {
            Resource.success(data = dataSource.getNumbers(range).mapNotNull {
                NumberFact(it.key, it.value)
            })
        } catch (exception: Exception) {
            Resource.error(data = null, message = exception.message ?: "Some Error")
        }

    }
}