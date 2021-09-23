package com.katyrin.testsibers.model.repository

import com.katyrin.testsibers.model.datasource.RemoteDataSource
import com.katyrin.testsibers.model.entities.PagingDto

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getListPokemon(offset: Int): PagingDto =
        remoteDataSource.getListPokemon(offset)
}