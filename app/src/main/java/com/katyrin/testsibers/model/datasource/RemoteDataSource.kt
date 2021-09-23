package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.PagingDTO

interface RemoteDataSource {
    suspend fun getListPokemon(offset: Int): PagingDTO
}