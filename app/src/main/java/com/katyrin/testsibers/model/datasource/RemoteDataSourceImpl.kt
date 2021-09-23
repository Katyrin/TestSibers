package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.PagingDto
import com.katyrin.testsibers.model.entities.Pokemon
import com.katyrin.testsibers.model.network.ApiService

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getListPokemon(offset: Int): PagingDto {
        val result = apiService.getListPokemon(offset)
        val pokemonList: MutableList<Pokemon> = mutableListOf()
        result.results.forEach { namedApiResource ->
            pokemonList.add(Pokemon(namedApiResource.name, getNumber(namedApiResource.url)))
        }
        return PagingDto(result.next, result.previous, pokemonList)
    }

    private fun getNumber(url: String): String {
        val list = url.split("/")
        val size = list.size
        return list[size - 2]
    }

    private suspend fun getPokemonImage(pokemonName: String): String? =
        apiService.getPokemonByName(pokemonName).sprites.frontDefault
}