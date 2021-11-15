package com.medhdj.oplay.features.programs.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medhdj.business.programs.GetProgramDetailsUseCase

import com.medhdj.core.extension.mapIsLoading
import com.medhdj.core.extension.mapSuccess
import com.medhdj.core.functionnal.Response
import com.medhdj.oplay.features.programs.ProgramUIModels
import com.medhdj.oplay.features.programs.ProgramUIModels.ProgramDetails.Companion.createDefaultProgramDetails
import com.medhdj.oplay.features.programs.toProgramDetailsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProgramDetailsViewModel @Inject constructor(
    private val getProgramDetailsUseCase: GetProgramDetailsUseCase,
    private val state: SavedStateHandle
) : ViewModel() {
    companion object {
        const val PROGRAM_KEY = "program"
    }

    private val _programDetailsData =
        MutableLiveData<Response<Throwable, ProgramUIModels.ProgramDetails>>()
    val programDetailsData = _programDetailsData.mapSuccess()
    val isLoadingData = _programDetailsData.mapIsLoading()

    init {
        fetchProgramDetails()
    }

    private fun fetchProgramDetails() {
        val program = state.get<ProgramUIModels.Program>(PROGRAM_KEY)!!
        program.detailLink?.let { detailsLink ->
            viewModelScope.launch {
                runCatching {
                    _programDetailsData.value = Response.Loading
                    val programDetails =
                        getProgramDetailsUseCase.getProgramDetails(detailLink = detailsLink)
                    _programDetailsData.value =
                        Response.Success(programDetails.toProgramDetailsUI(program))

                }.onFailure {
                    Timber.e(it)
                    _programDetailsData.value =
                        Response.Success(createDefaultProgramDetails(program))
                }
            }
        }
    }
}
