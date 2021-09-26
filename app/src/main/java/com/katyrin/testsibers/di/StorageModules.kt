package com.katyrin.testsibers.di

import androidx.room.Room
import com.katyrin.testsibers.model.storage.prefs.Storage
import com.katyrin.testsibers.model.storage.prefs.StorageImpl
import com.katyrin.testsibers.model.storage.room.PokemonDataBase
import org.koin.dsl.module

val database = module {
    single { Room.databaseBuilder(get(), PokemonDataBase::class.java, "PokemonDB").build() }
    single { get<PokemonDataBase>().getPokemonDAO() }
}

val preference = module {
    single<Storage> { StorageImpl(context = get()) }
}