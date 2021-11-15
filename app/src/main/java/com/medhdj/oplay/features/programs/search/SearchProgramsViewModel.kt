package com.medhdj.oplay.features.programs.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.medhdj.business.programs.SearchProgramsUseCase
import com.medhdj.core.extension.mapError
import com.medhdj.core.extension.mapSuccess
import com.medhdj.core.functionnal.Response
import com.medhdj.oplay.features.programs.ProgramUIModels
import com.medhdj.oplay.features.programs.toProgramUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProgramsViewModel @Inject constructor(
    private val searchProgramsUseCase: SearchProgramsUseCase
) : ViewModel() {

    private val pagingDataFlow =
        MutableLiveData<Response<Throwable, Flow<PagingData<ProgramUIModels.Program>>>>()

    val pagingData = pagingDataFlow.mapSuccess()
    val pagingDataError = pagingDataFlow.mapError()

    @SuppressWarnings("TooGenericExceptionCaught")
    fun searchPrograms(withTitle: String) {
        viewModelScope.launch {
            try {
                val result = searchProgramsUseCase.searchPrograms(withTitle = withTitle)
                    .map {
                        it.map { program ->
                            program.toProgramUI()
                        }
                    }
                    .cachedIn(viewModelScope)

                pagingDataFlow.value = Response.Success(result)
            } catch (ex: Exception) {
                pagingDataFlow.value = Response.Failure(ex)
            }
        }
    }
}
