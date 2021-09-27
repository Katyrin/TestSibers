package com.katyrin.testsibers.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.katyrin.testsibers.model.entities.PagingDTO
import com.katyrin.testsibers.model.entities.PokemonDTO
import com.katyrin.testsibers.model.repository.Repository
import com.katyrin.testsibers.utils.PAGE_SIZE
import com.katyrin.testsibers.utils.START_OFFSET
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(private val repository: Repository) : PagingSource<Int, PokemonDTO>() {

    var isRandom = false
    private var currentPage: Int = 0
    private var pokemonPosition: Int = 0

    override fun getRefreshKey(state: PagingState<Int, PokemonDTO>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE_POSITION)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE_POSITION)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDTO> =
        try {
            checkIsRandom()
            val nextPage: Int = params.key ?: pokemonPosition / PAGE_SIZE
            val pagingDTO: PagingDTO = getPagingDTO(nextPage)
            currentPage = nextPage
            getLoadResult(pagingDTO)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    private suspend fun checkIsRandom() {
        if (isRandom) pokemonPosition = (START_OFFSET..repository.getTotalSize()).random()
        isRandom = false
    }

    // Request to get a list of Pokemon, taking into account the shift from the starting position
    private suspend fun getPagingDTO(nextPage: Int): PagingDTO =
        if (0 <= nextPage) {
            val offset = nextPage * PAGE_SIZE + (pokemonPosition % PAGE_SIZE)
            repository.getListPokemon(offset, PAGE_SIZE)
        } else {
            repository.getListPokemon(START_OFFSET, pokemonPosition % PAGE_SIZE)
        }

    private fun getLoadResult(pagingDTO: PagingDTO): LoadResult<Int, PokemonDTO> =
        if (pagingDTO.pokemonDTOList.isNullOrEmpty()) LoadResult.Error(Throwable(EMPTY_DATA))
        else LoadResult.Page(
            data = pagingDTO.pokemonDTOList,
            prevKey = if (pagingDTO.previous.isNullOrEmpty()) null else currentPage - ONE_POSITION,
            nextKey = if (pagingDTO.next.isNullOrEmpty()) null else currentPage + ONE_POSITION
        )

    private companion object {
        const val ONE_POSITION = 1
        const val EMPTY_DATA = "Empty data"
    }
}