package com.colby.bloom_number_facts

import com.colby.bloom_number_facts.data.NumberFact
import com.colby.bloom_number_facts.data.NumberFactsRepository
import com.colby.bloom_number_facts.utils.Resource
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals


class NumberFactsRepositoryTest {

    private val dataSource = MockDataSource()
    private val repo = NumberFactsRepository(dataSource)

    //For times sake I will not write exhaustive tests here.
    // This is an example of what tests for the repo would look like.
    @Test
    fun wellFormedResponse_returnsSuccess() = runTest {
        dataSource.typeOfResponse = TypeOfResponse.WELL_FORMED

        assertEquals(
            Resource.success(
                data = listOf(
                    NumberFact(number = "1", fact = "Test"),
                    NumberFact(number = "2", fact = "Test2")
                )
            ), repo.getNumbers("")
        )
    }
}