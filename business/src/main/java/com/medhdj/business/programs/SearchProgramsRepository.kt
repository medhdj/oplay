package com.medhdj.business.programs

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SearchProgramsRepository {
    fun searchPrograms(titleToSearch: String): Flow<PagingData<Program>>
}
