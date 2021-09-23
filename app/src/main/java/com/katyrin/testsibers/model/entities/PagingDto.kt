package com.katyrin.testsibers.model.entities

data class PagingDto(
    val next: String?,
    val previous: String?,
    val pokemonList: List<Pokemon>
)
