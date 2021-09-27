package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResourceList

interface RemoteDataSource {
    //Retrieving a list of Pokemon from a remote source page by page.
    // Request contains starting point and page size
    suspend fun getListPokemon(offset: Int, limit: Int): NamedApiResourceList

    //Getting complete information about a Pokemon by name
    suspend fun getPokemonByName(pokemonName: String): PokemonDTO

    // Getting the size of the list of all pokemon
    suspend fun getTotalSize(): Int
}