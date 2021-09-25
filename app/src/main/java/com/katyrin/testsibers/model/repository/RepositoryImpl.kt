package com.katyrin.testsibers.model.repository

import com.katyrin.testsibers.model.datasource.LocalDataSource
import com.katyrin.testsibers.model.datasource.RemoteDataSource
import com.katyrin.testsibers.model.entities.CashData
import com.katyrin.testsibers.model.entities.PagingDTO
import com.katyrin.testsibers.model.entities.PokemonDTO

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getListPokemon(offset: Int, limit: Int): PagingDTO =
        with(remoteDataSource.getListPokemon(offset, limit)) {
            val cashData: CashData = localDataSource.getCashData(results)
            val cashList: MutableList<PokemonDTO> = cashData.cashList.toMutableList()
            cashData.noCashList.forEach { noCashData ->
                val newPokemonDTO: PokemonDTO = remoteDataSource.getPokemonByName(noCashData.first)
                cashList.add(noCashData.second, newPokemonDTO)
                localDataSource.putPokemonList(newPokemonDTO)
            }
            PagingDTO(next, previous, cashList)
        }

    override suspend fun getTotalSize(): Int = remoteDataSource.getTotalSize()
}