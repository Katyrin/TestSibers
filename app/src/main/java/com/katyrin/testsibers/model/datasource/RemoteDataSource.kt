package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResourceList

interface RemoteDataSource {
    suspend fun getListPokemon(offset: Int, limit: Int): NamedApiResourceList
    suspend fun getPokemonByName(pokemonName: String): PokemonDTO
    suspend fun getTotalSize(): Int
}