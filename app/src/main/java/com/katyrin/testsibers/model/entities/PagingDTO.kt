package com.katyrin.testsibers.model.entities

data class PagingDTO(
    val next: String?,
    val previous: String?,
    val pokemonList: List<Pokemon>
)
