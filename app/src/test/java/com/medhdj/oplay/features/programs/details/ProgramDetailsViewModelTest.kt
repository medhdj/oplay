package com.medhdj.oplay.features.programs.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.medhdj.business.programs.GetProgramDetailsUseCase
import com.medhdj.business.programs.ProgramDetailsFixtures
import com.medhdj.business.programs.ProgramFixtures
import com.medhdj.oplay.features.common.CoroutineTestRule
import com.medhdj.oplay.features.common.test
import com.medhdj.oplay.features.programs.details.ProgramDetailsViewModel.Companion.PROGRAM_KEY
import com.medhdj.oplay.features.programs.toProgramUI
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class ProgramDetailsViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    private val programUI = ProgramFixtures.Builder().build().toProgramUI()
    private val getProgramDetailsUseCase: GetProgramDetailsUseCase = mockk()
    private var state = SavedStateHandle(mapOf(PROGRAM_KEY to programUI))

    private val tested by lazy {
        ProgramDetailsViewModel(getProgramDetailsUseCase, state)
    }

    @Test
    fun `verify that init execute as expected`() {
        runBlockingTest {
            // given
            coEvery {
                getProgramDetailsUseCase.getProgramDetails(any())
            } returns mockk(relaxed = true)

            // when
            tested

            // then
            coVerify(exactly = 1) { getProgramDetailsUseCase.getProgramDetails(any()) }
        }
    }

    @Test
    fun `verify that we get expected live data in case of success`() {
        // given
        coEvery {
            getProgramDetailsUseCase.getProgramDetails(any())
        } returns ProgramDetailsFixtures.Builder().build()

        // when
        val articleDataObserver = tested.programDetailsData.test()
        val errorObserver = tested.isLoadingData.test()

        // then
        errorObserver.assertHasValue {
            // not a failure
            !it
        }
        articleDataObserver.assertHasValue {
            it.program.id == programUI.id &&
                    it.pitch == "pitch"
        }
    }
}
