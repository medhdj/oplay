package com.medhdj.business.programs

interface GetProgramDetailsRepository {
    suspend fun getProgramDetails(detailLink: String): ProgramDetails?
}
