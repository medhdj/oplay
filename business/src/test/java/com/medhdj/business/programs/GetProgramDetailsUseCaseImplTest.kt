package com.medhdj.business.programs

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.junit.Test

class GetProgramDetailsUseCaseImplTest {

    private val getProgramDetailsRepository: GetProgramDetailsRepository = mockk()

    private val tested = GetProgramDetailsUseCaseImpl(getProgramDetailsRepository)

    @Test
    fun `test fetching data successfully`() {
        runBlocking {
            // given
            val expected = ProgramDetailsFixtures.Builder().build()
            coEvery { getProgramDetailsRepository.getProgramDetails(any()) } returns expected

            // when
            val result = tested.getProgramDetails("link")

            //then
            result?.pitch `should be equal to` expected.pitch
        }
    }

    @Test(expected = Exception::class)
    fun `test fetching data with errors`() {
        runBlocking {
            // given
            coEvery { getProgramDetailsRepository.getProgramDetails(any()) } throws Exception("some error")

            // when
            tested.getProgramDetails("link")

            //then
            // should throw an error
        }
    }
}
