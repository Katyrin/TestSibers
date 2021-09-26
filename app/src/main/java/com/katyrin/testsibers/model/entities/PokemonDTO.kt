package com.katyrin.testsibers.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDTO(
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val attack: Int,
    val defense: Int,
    val hp: Int,
    val imageUrl: String? = "",
    val id: Int,
    var isFirst: Boolean = false
) : Parcelable
