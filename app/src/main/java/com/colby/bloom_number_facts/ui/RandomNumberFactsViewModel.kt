package com.colby.bloom_number_facts.ui

import androidx.lifecycle.*
import com.colby.bloom_number_facts.utils.Status
import com.colby.bloom_number_facts.data.NumberFactsRepository
import com.colby.bloom_number_facts.data.NumberRepository
import kotlinx.coroutines.launch

private const val FACTS_DESIRED = 20

class RandomNumberFactsViewModel(private val repo: NumberRepository = NumberFactsRepository()) :
    ViewModel() {

    private val _uiState = MutableLiveData<RandomNumberFactsUiState>(RandomNumberFactsUiState(isLoading = true))
    val uiState: LiveData<RandomNumberFactsUiState>
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