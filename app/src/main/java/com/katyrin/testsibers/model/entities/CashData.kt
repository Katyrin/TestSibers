package com.katyrin.testsibers.model.entities

data class CashData(
    val cashList: List<PokemonDTO>,
    val noCashList: List<Pair<String, Int>>
)
