package com.colby.bloom_number_facts.ui

import androidx.lifecycle.*
import com.colby.bloom_number_facts.utils.Status
import com.colby.bloom_number_facts.data.NumbersRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val FACTS_DESIRED = 20

class NumberCollectorViewModel(private val repo: NumbersRepository = NumbersRepository()) :
    ViewModel() {

    private val _uiState = MutableLiveData<NumberUiState>(NumberUiState(isLoading = true))
    val uiState: LiveData<NumberUiState>
        get() = _uiState

    init {
        getNumbers()
    }

    private fun getNumbers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(isLoading = true)
            val result = repo.getNumbers(createRandomRange())

            when (result.status) {
                Status.SUCCESS -> _uiState.value =
                    _uiState.value?.copy(
                        numberFacts = result.data,
                        isLoading = false
                    )
                Status.ERROR -> _uiState.value = _uiState.value?.copy(errorMessage = result.message)
            }
        }
    }

    //Using shuffle vs random to ensure we have unique numbers. No need to have duplicate facts.
    private fun createRandomRange(): String =
        (1..100).shuffled().take(FACTS_DESIRED).map { it.toString() }.reduce { l, r -> "$l,$r" }

    fun errorShown() {
        _uiState.value = _uiState.value?.copy(errorMessage = null)
    }
}