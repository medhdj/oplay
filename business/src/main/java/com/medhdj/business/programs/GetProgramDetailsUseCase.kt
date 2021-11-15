package com.medhdj.business.programs

interface GetProgramDetailsUseCase {
    suspend fun getProgramDetails(detailLink: String): ProgramDetails?
}

class GetProgramDetailsUseCaseImpl(private val getProgramDetailsRepository: GetProgramDetailsRepository) :
    GetProgramDetailsUseCase {
    override suspend fun getProgramDetails(detailLink: String): ProgramDetails? =
        getProgramDetailsRepository.getProgramDetails(detailLink = detailLink)
}
