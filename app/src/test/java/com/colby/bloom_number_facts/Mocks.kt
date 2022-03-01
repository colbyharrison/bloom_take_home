package com.colby.bloom_number_facts

import com.colby.bloom_number_facts.data.NumberFact
import com.colby.bloom_number_facts.data.NumberRepository
import com.colby.bloom_number_facts.data.NumbersDataSource
import com.colby.bloom_number_facts.utils.Resource
import java.lang.Exception

enum class TypeOfResponse {
    WELL_FORMED,
    EMPTY,
    MALFORMED
}

class MockDataSource(var typeOfResponse: TypeOfResponse = TypeOfResponse.WELL_FORMED) :
    NumbersDataSource {
    private val wellFormedResponse = mapOf(Pair("1", "Test"), Pair("2", "Test2"))

    override suspend fun getNumbers(range: String): Map<String, String> =
        when (typeOfResponse) {
            TypeOfResponse.WELL_FORMED -> wellFormedResponse
            TypeOfResponse.EMPTY -> emptyMap()
            TypeOfResponse.MALFORMED -> throw Exception("Error")
        }
}

class MockRepository(var typeOfResponse: TypeOfResponse = TypeOfResponse.WELL_FORMED) :
    NumberRepository {
    override suspend fun getNumbers(range: String): Resource<List<NumberFact>> =
        when (typeOfResponse) {
            TypeOfResponse.WELL_FORMED -> Resource.success(
                data = listOf(
                    NumberFact("1", "Test"),
                    NumberFact("2", "Test2")
                )
            )
            TypeOfResponse.EMPTY -> Resource.success(data = emptyList())
            TypeOfResponse.MALFORMED -> Resource.error(data = null, message = "Error")
        }

}