package com.katyrin.testsibers.model.repository

import com.katyrin.testsibers.model.datasource.RemoteDataSource
import com.katyrin.testsibers.model.entities.PagingDTO

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override suspend fun getListPokemon(offset: Int, limit: Int): PagingDTO =
        remoteDataSource.getListPokemon(offset, limit)

    override suspend fun getTotalSize(): Int = remoteDataSource.getTotalSize()
}