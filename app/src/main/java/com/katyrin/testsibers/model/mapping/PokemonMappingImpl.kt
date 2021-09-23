package com.katyrin.testsibers.model.mapping

import com.katyrin.testsibers.model.entities.PagingDTO
import com.katyrin.testsibers.model.entities.Pokemon
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResourceList

class PokemonMappingImpl : PokemonMapping {

    override fun mapResponseDataToPagingDTO(namedApiResourceList: NamedApiResourceList): PagingDTO {
        val pokemonList: MutableList<Pokemon> = mutableListOf()
        namedApiResourceList.results.forEach { namedApiResource ->
            pokemonList.add(Pokemon(namedApiResource.name, getPokemonNumber(namedApiResource.url)))
        }
        return PagingDTO(namedApiResourceList.next, namedApiResourceList.previous, pokemonList)
    }

    private fun getPokemonNumber(url: String): String =
        with(url.split(DELIMITER)) { get(size - TWO_POSITIONS) }

    private companion object {
        const val DELIMITER = "/"
        const val TWO_POSITIONS = 2
    }
}