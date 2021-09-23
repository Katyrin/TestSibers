package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.PagingDto

interface RemoteDataSource {
    suspend fun getListPokemon(offset: Int): PagingDto
}