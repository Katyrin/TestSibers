package com.katyrin.testsibers.di

import androidx.paging.PagingSource
import com.katyrin.testsibers.model.datasource.LocalDataSource
import com.katyrin.testsibers.model.datasource.LocalDataSourceImpl
import com.katyrin.testsibers.model.datasource.RemoteDataSource
import com.katyrin.testsibers.model.datasource.RemoteDataSourceImpl
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.mapping.PokemonMapping
import com.katyrin.testsibers.model.mapping.PokemonMappingImpl
import com.katyrin.testsibers.model.networkstate.NetworkState
import com.katyrin.testsibers.model.networkstate.NetworkStateImpl
import com.katyrin.testsibers.model.paging.PokemonPagingSource
import com.katyrin.testsibers.model.repository.Repository
import com.katyrin.testsibers.model.repository.RepositoryImpl
import com.katyrin.testsibers.viewmodel.HomeViewModel
import org.koin.dsl.module

val application = module {
    single<PokemonMapping> { PokemonMappingImpl() }
    factory<NetworkState> { NetworkStateImpl(context = get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(apiService = get(), pokemonMapping = get()) }
    single<LocalDataSource> { LocalDataSourceImpl(pokemonDAO = get(), pokemonMapping = get()) }
    single<Repository> {
        RepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            networkState = get(),
            storage = get()
        )
    }
    factory<PagingSource<Int, PokemonDTO>> { PokemonPagingSource(repository = get()) }
}

val homeModule = module {
    factory { HomeViewModel(pagingSource = get()) }
}