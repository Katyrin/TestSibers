package com.katyrin.testsibers.model.entities.pokemonDTO

data class NamedApiResourceList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource>
)
