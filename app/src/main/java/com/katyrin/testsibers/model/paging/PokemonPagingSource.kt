package com.katyrin.testsibers.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.katyrin.testsibers.model.entities.PagingDTO
import com.katyrin.testsibers.model.entities.Pokemon
import com.katyrin.testsibers.model.repository.Repository
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(private val repository: Repository) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE_POSITION)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE_POSITION)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> = try {
        val position = params.key ?: START_OFFSET
        val pagingDTO: PagingDTO = repository.getListPokemon(position * PAGE_SIZE)
        if (pagingDTO.pokemonList.isNullOrEmpty()) LoadResult.Error(Throwable(EMPTY_DATA))
        else getLoadResult(pagingDTO, position)
    } catch (exception: IOException) {
        LoadResult.Error(exception)
    } catch (exception: HttpException) {
        LoadResult.Error(exception)
    }

    private fun getLoadResult(pagingDTO: PagingDTO, position: Int): LoadResult<Int, Pokemon> =
        LoadResult.Page(
            data = pagingDTO.pokemonList,
            prevKey = if (pagingDTO.previous.isNullOrEmpty()) null else position - ONE_POSITION,
            nextKey = if (pagingDTO.next.isNullOrEmpty()) null else position + ONE_POSITION
        )

    private companion object {
        const val PAGE_SIZE = 30
        const val ONE_POSITION = 1
        const val START_OFFSET = 0
        const val EMPTY_DATA = "Empty data"
    }
}