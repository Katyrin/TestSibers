package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.CashData
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResource

interface LocalDataSource {
    suspend fun getPokemonList(): List<PokemonDTO>
    suspend fun getPokemonAttackList(): List<PokemonDTO>
    suspend fun getPokemonDefenseList(): List<PokemonDTO>
    suspend fun getPokemonHPList(): List<PokemonDTO>
    suspend fun putPokemonList(pokemonDTO: PokemonDTO)
    suspend fun getCashData(namedApiResourceList: List<NamedApiResource>): CashData
}