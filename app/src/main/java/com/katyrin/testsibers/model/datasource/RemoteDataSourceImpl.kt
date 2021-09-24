package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.PagingDTO
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResourceList
import com.katyrin.testsibers.model.mapping.PokemonMapping
import com.katyrin.testsibers.model.network.ApiService

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private var pokemonMapping: PokemonMapping
) : RemoteDataSource {

    override suspend fun getListPokemon(offset: Int, limit: Int): PagingDTO {
        val namedApiResourceList: NamedApiResourceList = apiService.getListPokemon(offset, limit)
        val pokemonList: MutableList<PokemonDTO> = mutableListOf()
        namedApiResourceList.results.forEach { namedApiResource ->
            pokemonList.add(getPokemonByName(namedApiResource.name))
        }
        return PagingDTO(namedApiResourceList.next, namedApiResourceList.previous, pokemonList)
    }

    private suspend fun getPokemonByName(pokemonName: String): PokemonDTO =
        pokemonMapping.mapPokemonToPokemonDTO(apiService.getPokemonByName(pokemonName))

    override suspend fun getTotalSize(): Int = apiService.getListPokemon().count
}