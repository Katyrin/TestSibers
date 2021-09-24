package com.katyrin.testsibers.model.network

import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResourceList
import com.katyrin.testsibers.model.entities.pokemonDTO.Pokemon
import com.katyrin.testsibers.utils.PAGE_SIZE
import com.katyrin.testsibers.utils.START_OFFSET
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("offset") offset: Int = START_OFFSET,
        @Query("limit") limit: Int = PAGE_SIZE
    ): NamedApiResourceList

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Pokemon
}