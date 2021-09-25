package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResourceList
import com.katyrin.testsibers.model.mapping.PokemonMapping
import com.katyrin.testsibers.model.network.ApiService

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private var pokemonMapping: PokemonMapping
) : RemoteDataSource {

    override suspend fun getListPokemon(offset: Int, limit: Int): NamedApiResourceList =
        apiService.getListPokemon(offset, limit)

    override suspend fun getPokemonByName(pokemonName: String): PokemonDTO =
        pokemonMapping.mapPokemonToPokemonDTO(apiService.getPokemonByName(pokemonName))

    override suspend fun getTotalSize(): Int = apiService.getListPokemon().count
}