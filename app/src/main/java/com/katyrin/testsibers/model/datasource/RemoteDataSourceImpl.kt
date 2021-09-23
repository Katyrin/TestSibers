package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.PagingDTO
import com.katyrin.testsibers.model.mapping.PokemonMapping
import com.katyrin.testsibers.model.network.ApiService

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private var pokemonMapping: PokemonMapping
) : RemoteDataSource {

    override suspend fun getListPokemon(offset: Int): PagingDTO =
        pokemonMapping.mapResponseDataToPagingDTO(apiService.getListPokemon(offset))
}