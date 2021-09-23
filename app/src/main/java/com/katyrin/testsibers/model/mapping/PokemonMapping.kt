package com.katyrin.testsibers.model.mapping

import com.katyrin.testsibers.model.entities.PagingDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResourceList

interface PokemonMapping {
    fun mapResponseDataToPagingDTO(namedApiResourceList: NamedApiResourceList): PagingDTO
}