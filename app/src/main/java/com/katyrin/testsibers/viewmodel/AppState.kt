package com.katyrin.testsibers.viewmodel

import androidx.paging.PagingData
import com.katyrin.testsibers.model.entities.Pokemon

sealed class AppState {
    data class Success(val pokemonList: PagingData<Pokemon>) : AppState()
    data class Error(val errorState: ErrorState) : AppState()
}