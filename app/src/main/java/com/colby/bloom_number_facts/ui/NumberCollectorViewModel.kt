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

    private fun createRandomRange(): String =
        (1..FACTS_DESIRED).map { Random.nextInt(100).toString() }.reduce { l, r -> "$l,$r" }

}