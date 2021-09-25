package com.katyrin.testsibers.model.mapping

import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.PokemonEntity
import com.katyrin.testsibers.model.entities.pokemonDTO.Pokemon

interface PokemonMapping {
    fun mapPokemonToPokemonDTO(pokemon: Pokemon): PokemonDTO
    fun mapEntityListToDTOList(pokemonEntityList: List<PokemonEntity>): List<PokemonDTO>
    fun mapListToDTO(pokemonEntity: PokemonEntity): PokemonDTO
    fun mapDTOToEntity(pokemonDTO: PokemonDTO): PokemonEntity
}