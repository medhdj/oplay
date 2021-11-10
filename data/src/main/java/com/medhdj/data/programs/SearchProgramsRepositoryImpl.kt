package com.medhdj.data.programs

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.medhdj.business.programs.Program
import com.medhdj.business.programs.SearchProgramsRepository
import kotlinx.coroutines.flow.Flow

class SearchProgramsRepositoryImpl(
    private val programsFeedDataSource: ProgramsFeedDataSource
) : SearchProgramsRepository {
    override fun searchPrograms(titleToSearch: String): Flow<PagingData<Program>> =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_LIMIT,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                programsFeedDataSource.programsFeedQuery =
                    ProgramsFeedDataSource.ProgramsFeedQuery(
                        titleToSearch = "title=$titleToSearch"
                    )
                programsFeedDataSource
            }
        ).flow
}

private const val DEFAULT_PAGE_LIMIT = 30
