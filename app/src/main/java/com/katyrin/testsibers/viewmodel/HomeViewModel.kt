package com.katyrin.testsibers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.paging.PokemonPagingSource
import com.katyrin.testsibers.utils.PAGE_SIZE
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HomeViewModel(private val pagingSource: PagingSource<Int, PokemonDTO>) : ViewModel() {

    private val _mutableLiveData: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState>
        get() = _mutableLiveData

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable -> handlerError(throwable) }
    )

    private fun handlerError(throwable: Throwable) {
        _mutableLiveData.value = when (throwable) {
            is SocketTimeoutException -> AppState.Error(ErrorState.TimOut)
            is UnknownHostException -> AppState.Error(ErrorState.UnknownHost)
            is ConnectionShutdownException -> AppState.Error(ErrorState.Connection)
            is IOException -> AppState.Error(ErrorState.Server)
            else -> AppState.Error(ErrorState.Unknown(throwable.message))
        }
    }

    private fun pokemonDTOListFlow(): Flow<PagingData<PokemonDTO>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 5),
            pagingSourceFactory = { pagingSource }
        ).flow.cachedIn(viewModelScope)

    fun getListPokemon() {
        cancelJob()
        viewModelCoroutineScope.launch {
            pokemonDTOListFlow().collectLatest { pokemonList ->
                _mutableLiveData.value = AppState.Success(pokemonList)
            }
        }
    }

    fun randomStart() {
        if (pagingSource is PokemonPagingSource) pagingSource.isRandom = true
        getListPokemon()
    }

    init {
        getListPokemon()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        cancelJob()
        super.onCleared()
    }
}