package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.CashData
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResource

interface LocalDataSource {
    //Getting a list of Pokemon available in the database
    suspend fun getPokemonList(): List<PokemonDTO>

    //Adding a Pokemon to the Database
    suspend fun putPokemonList(pokemonDTO: PokemonDTO)

    //Getting an object according to the list of Pokemon names
    // that contains a list of available Pokemon in the database
    // and a list of Pokemon names that need to be downloaded from a remote source
    suspend fun getCashData(namedApiResourceList: List<NamedApiResource>): CashData
}