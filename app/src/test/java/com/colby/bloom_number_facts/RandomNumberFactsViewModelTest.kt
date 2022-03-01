package com.colby.bloom_number_facts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.colby.bloom_number_facts.data.NumberFact
import com.colby.bloom_number_facts.ui.RandomNumberFactsViewModel
import com.colby.bloom_number_facts.ui.RandomNumberFactsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals


@kotlinx.coroutines.ExperimentalCoroutinesApi
class RandomNumberFactsViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repo = MockRepository()
    private lateinit var viewModel: RandomNumberFactsViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    //For times sake I will not write exhaustive tests here.
    // This is an example of what tests for the viewModel would look like.
    @Test
    fun initialCreation() = runTest {
        repo.typeOfResponse = TypeOfResponse.WELL_FORMED
        viewModel = RandomNumberFactsViewModel(repo)
        assertEquals(RandomNumberFactsUiState(isLoading = true), viewModel.uiState.value)
        advanceUntilIdle()

        assertEquals(
            RandomNumberFactsUiState(
                numberFacts = listOf(
                    NumberFact("1", "Test"),
                    NumberFact("2", "Test2")
                ),
                isLoading = false
            ), viewModel.uiState.value
        )
    }

}

