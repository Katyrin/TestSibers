package com.katyrin.testsibers.di

import androidx.paging.PagingSource
import com.katyrin.testsibers.model.datasource.RemoteDataSource
import com.katyrin.testsibers.model.datasource.RemoteDataSourceImpl
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.mapping.PokemonMapping
import com.katyrin.testsibers.model.mapping.PokemonMappingImpl
import com.katyrin.testsibers.model.paging.PokemonPagingSource
import com.katyrin.testsibers.model.repository.Repository
import com.katyrin.testsibers.model.repository.RepositoryImpl
import com.katyrin.testsibers.viewmodel.HomeViewModel
import org.koin.dsl.module

val application = module {
    single<PokemonMapping> { PokemonMappingImpl() }
    single<RemoteDataSource> { RemoteDataSourceImpl(apiService = get(), pokemonMapping = get()) }
    single<Repository> { RepositoryImpl(remoteDataSource = get()) }
    factory<PagingSource<Int, PokemonDTO>> { PokemonPagingSource(repository = get()) }
}

val homeModule = module {
    factory { HomeViewModel(pagingSource = get()) }
}