package com.medhdj.data.programs


import com.medhdj.business.programs.ProgramDetails
import com.medhdj.data.common.CoroutineTestRule
import com.medhdj.data.common.OcsApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetProgramDetailsRepositoryImplTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val ocsApi: OcsApi = mockk()
    private val tested = GetProgramDetailsRepositoryImpl(ocsApi)

    @Test
    fun `test successful fetch`() {
        runBlocking {
            // given
            val expected = ProgramDetailsEntityFixtures.Builder().build()
            coEvery {
                ocsApi.getProgramDetails(any())
            } returns expected

            // when
            var programDetails = tested.getProgramDetails("link")

            // then
            programDetails?.pitch `should be equal to` expected.pitch
        }
    }

    @Test(expected = CustomTestException::class)
    fun `test unsuccessful fetch`() {
        runBlocking {
            // given

            coEvery {
                ocsApi.getProgramDetails(any())
            } throws CustomTestException("some error")

            // when
            tested.getProgramDetails("link")

            // then
            // throws error
        }
    }

    @Test
    fun `test correct mapping to business model`() {
        runBlocking {
            // given
            val expected = ProgramDetailsEntityFixtures.Builder(
                pitch = "abc"
            ).build()
            coEvery {
                ocsApi.getProgramDetails(any())
            } returns expected

            // when
            var programDetails = tested.getProgramDetails("link")

            // then
            programDetails `should be instance of` ProgramDetails::class
            programDetails?.pitch `should be equal to` expected.pitch
        }
    }

    class CustomTestException(msg: String) : Exception(msg)
}
