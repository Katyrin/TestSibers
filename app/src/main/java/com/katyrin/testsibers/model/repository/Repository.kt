package com.katyrin.testsibers.model.repository

import com.katyrin.testsibers.model.entities.PagingDTO

interface Repository {
    suspend fun getListPokemon(offset: Int): PagingDTO
}