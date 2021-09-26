package com.katyrin.testsibers.model.datasource

import com.katyrin.testsibers.model.entities.CashData
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.entities.PokemonEntity
import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResource
import com.katyrin.testsibers.model.mapping.PokemonMapping
import com.katyrin.testsibers.model.storage.room.PokemonDAO

class LocalDataSourceImpl(
    private val pokemonDAO: PokemonDAO,
    private val pokemonMapping: PokemonMapping
) : LocalDataSource {

    override suspend fun getPokemonList(): List<PokemonDTO> =
        pokemonMapping.mapEntityListToDTOList(pokemonDAO.getPokemonList())

    override suspend fun getPokemonAttackList(): List<PokemonDTO> =
        pokemonMapping.mapEntityListToDTOList(pokemonDAO.getPokemonAttackList())

    override suspend fun getPokemonDefenseList(): List<PokemonDTO> =
        pokemonMapping.mapEntityListToDTOList(pokemonDAO.getPokemonDefenseList())

    override suspend fun getPokemonHPList(): List<PokemonDTO> =
        pokemonMapping.mapEntityListToDTOList(pokemonDAO.getPokemonHPList())

    override suspend fun putPokemonList(pokemonDTO: PokemonDTO) {
        pokemonDAO.putPokemonList(pokemonMapping.mapDTOToEntity(pokemonDTO))
    }

    override suspend fun getCashData(namedApiResourceList: List<NamedApiResource>): CashData {
        val cashList: MutableList<PokemonDTO> = mutableListOf()
        val noCashList: MutableList<Pair<String, Int>> = mutableListOf()
        var position = 0
        namedApiResourceList.forEach { namedApiResource ->
            val pokemonEntity: PokemonEntity? = pokemonDAO.getPokemonByName(namedApiResource.name)
            if (pokemonEntity == null) noCashList.add(namedApiResource.name to position)
            else cashList.add(pokemonMapping.mapListToDTO(pokemonEntity))
            position++
        }
        return CashData(cashList, noCashList)
    }
}