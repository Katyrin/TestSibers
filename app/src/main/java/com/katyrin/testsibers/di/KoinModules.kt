package com.katyrin.testsibers.di

import androidx.paging.PagingSource
import com.katyrin.testsibers.model.datasource.RemoteDataSource
import com.katyrin.testsibers.model.datasource.RemoteDataSourceImpl
import com.katyrin.testsibers.model.entities.Pokemon
import com.katyrin.testsibers.model.repository.Repository
import com.katyrin.testsibers.model.repository.RepositoryImpl
import com.katyrin.testsibers.viewmodel.HomeViewModel
import com.katyrin.testsibers.model.paging.PokemonPagingSource
import org.koin.dsl.module

val application = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(apiService = get()) }
    single<Repository> { RepositoryImpl(remoteDataSource = get()) }
    single<PagingSource<Int, Pokemon>> { PokemonPagingSource(repository = get()) }
}

val homeModule = module {
    factory { HomeViewModel(pagingSource = get()) }
}