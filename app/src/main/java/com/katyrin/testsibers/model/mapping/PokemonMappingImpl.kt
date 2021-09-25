package com.katyrin.testsibers.model.mapping

import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.PokemonEntity
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
            sprites.frontDefault,
            id
        )
    }

    override fun mapEntityListToDTOList(pokemonEntityList: List<PokemonEntity>): List<PokemonDTO> =
        mutableListOf<PokemonDTO>().also { listDTO ->
            pokemonEntityList.forEach { listEntity -> listDTO.add(mapListToDTO(listEntity)) }
        }

    override fun mapListToDTO(pokemonEntity: PokemonEntity): PokemonDTO = with(pokemonEntity) {
        PokemonDTO(name, height, weight, types, attack, defense, hp, imageUrl, id)
    }

    override fun mapDTOToEntity(pokemonDTO: PokemonDTO): PokemonEntity = with(pokemonDTO) {
        PokemonEntity(name, height, weight, types, attack, defense, hp, imageUrl, id)
    }

    private companion object {
        const val HP = 0
        const val ATTACK = 1
        const val DEFENCE = 2
    }
}