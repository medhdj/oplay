package com.medhdj.business.programs

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SearchProgramsUseCase {
    fun searchPrograms(withTitle: String): Flow<PagingData<Program>>
}

class SearchProgramsUseCaseImpl(private val searchProgramsRepository: SearchProgramsRepository) :
    SearchProgramsUseCase {
    override fun searchPrograms(withTitle: String): Flow<PagingData<Program>> =
        searchProgramsRepository.searchPrograms(titleToSearch = withTitle)
}
