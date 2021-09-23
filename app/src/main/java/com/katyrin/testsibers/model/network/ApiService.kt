package com.katyrin.testsibers.model.network

import com.katyrin.testsibers.model.entities.pokemonDTO.NamedApiResourceList
import com.katyrin.testsibers.model.entities.pokemonDTO.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon?limit=30")
    suspend fun getListPokemon(
        @Query("offset") offset: Int
    ): NamedApiResourceList

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Pokemon
}