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
    val defence: Int,
    val hp: Int,
    val imageUrl: String?
) : Parcelable
