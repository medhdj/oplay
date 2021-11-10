package com.medhdj.data.programs

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.medhdj.business.programs.Program
import com.medhdj.data.common.OcsApi
import com.medhdj.data.common.OcsApi.Companion.QUERY_PARAM_OFFSET_NAME
import com.medhdj.data.common.SearchResponse
import retrofit2.HttpException
import java.io.IOException

class ProgramsFeedDataSource(
    private val ocsApi: OcsApi
) : PagingSource<Int, Program>() {
    lateinit var programsFeedQuery: ProgramsFeedQuery

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Program> {
        val offset = params.key ?: DEFAULT_PAGE_OFFSET

        return try {
            val searchResponse = ocsApi.searchPrograms(
                title = programsFeedQuery.titleToSearch,
                limit = params.loadSize,
                offset = offset
            )

            val (previousKey, nextKey) = calculatePageOffsets(searchResponse)

            LoadResult.Page(
                data = searchResponse.data.toPrograms(),
                prevKey = previousKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Program>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }

    private fun calculatePageOffsets(searchResponse: SearchResponse<ProgramEntity>): Pair<Int?, Int?> {
        val previous = searchResponse.previous?.let {
            Uri.parse(it).getQueryParameter(QUERY_PARAM_OFFSET_NAME)?.toInt()!!
        }

        val next = searchResponse.next?.let {
            Uri.parse(it).getQueryParameter(QUERY_PARAM_OFFSET_NAME)?.toInt()!!
        }
        return Pair(previous, next)
    }

    data class ProgramsFeedQuery(
        val titleToSearch: String
    )
}

private const val DEFAULT_PAGE_OFFSET = 1
