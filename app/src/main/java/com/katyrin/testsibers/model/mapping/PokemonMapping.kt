package com.katyrin.testsibers.model.mapping

import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.Pokemon

interface PokemonMapping {
    fun mapPokemonToPokemonDTO(pokemon: Pokemon): PokemonDTO
}