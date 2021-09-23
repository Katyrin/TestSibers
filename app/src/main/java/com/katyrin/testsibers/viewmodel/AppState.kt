package com.katyrin.testsibers.viewmodel

import androidx.paging.PagingData
import com.katyrin.testsibers.model.entities.PokemonDTO

sealed class AppState {
    data class Success(val pokemonDTOList: PagingData<PokemonDTO>) : AppState()
    data class Error(val errorState: ErrorState) : AppState()
}