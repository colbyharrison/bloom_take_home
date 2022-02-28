package com.colby.bloom_number_facts.ui

import com.colby.bloom_number_facts.data.NumberFact

data class NumberUiState(
    val numberFacts: List<NumberFact>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)