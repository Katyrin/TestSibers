package com.katyrin.testsibers.model.repository

import com.katyrin.testsibers.model.entities.PagingDTO

interface Repository {
    //Retrieving a list of Pokemon from a remote source page by page.
    // Request contains starting point and page size
    suspend fun getListPokemon(offset: Int, limit: Int): PagingDTO

    // Getting the size of the list of all pokemon
    suspend fun getTotalSize(): Int
}