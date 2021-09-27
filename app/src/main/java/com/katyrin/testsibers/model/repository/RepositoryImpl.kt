package com.katyrin.testsibers.model.repository

import com.katyrin.testsibers.model.datasource.LocalDataSource
import com.katyrin.testsibers.model.datasource.RemoteDataSource
import com.katyrin.testsibers.model.entities.CashData
import com.katyrin.testsibers.model.entities.PagingDTO
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.networkstate.NetworkState
import com.katyrin.testsibers.model.storage.prefs.Storage
import com.katyrin.testsibers.utils.FIRST_ITEM

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkState: NetworkState,
    private val storage: Storage
) : Repository {

    override suspend fun getListPokemon(offset: Int, limit: Int): PagingDTO =
        if (networkState.isOnline() && !storage.isChecked()) getRemoteData(offset, limit)
        else getLocalData()

    //Obtaining available pokemon from the database.
    // If the pokemon is not in the database then download it from a remote source
    private suspend fun getRemoteData(offset: Int, limit: Int): PagingDTO =
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

    // Getting a list of Pokemon based on the selected sorting
    private suspend fun getLocalData(): PagingDTO =
        with(localDataSource.getPokemonList().toMutableList()) {
            if (storage.getAttackIsCheck()) sortByDescending { it.attack }
            if (storage.getDefenseIsCheck()) sortByDescending { it.defense }
            if (storage.getHPIsCheck()) sortByDescending { it.hp }
            if (storage.isChecked()) get(FIRST_ITEM).isFirst = true
            PagingDTO(null, null, this)
        }

    override suspend fun getTotalSize(): Int = remoteDataSource.getTotalSize()
}