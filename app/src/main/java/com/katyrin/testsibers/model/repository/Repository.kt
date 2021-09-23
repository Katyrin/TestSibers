package com.katyrin.testsibers.model.repository

import com.katyrin.testsibers.model.entities.PagingDto

interface Repository {
    suspend fun getListPokemon(offset: Int): PagingDto
}