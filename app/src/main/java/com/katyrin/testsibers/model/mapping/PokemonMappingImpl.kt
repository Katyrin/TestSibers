package com.katyrin.testsibers.model.mapping

import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.pokemonDTO.Pokemon

class PokemonMappingImpl : PokemonMapping {

    override fun mapPokemonToPokemonDTO(pokemon: Pokemon): PokemonDTO = with(pokemon) {
        val typeList: MutableList<String> = mutableListOf()
        types.forEach { typeList.add(it.type.name) }
        PokemonDTO(
            name,
            height,
            weight,
            typeList,
            stats[ATTACK].baseStat,
            stats[DEFENCE].baseStat,
            stats[HP].baseStat,
            sprites.frontDefault
        )
    }

    private companion object {
        const val HP = 0
        const val ATTACK = 1
        const val DEFENCE = 2
    }
}