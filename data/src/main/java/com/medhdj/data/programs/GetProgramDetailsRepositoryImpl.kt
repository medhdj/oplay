package com.medhdj.data.programs

import com.medhdj.business.programs.GetProgramDetailsRepository
import com.medhdj.business.programs.ProgramDetails
import com.medhdj.data.common.OcsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProgramDetailsRepositoryImpl(private val ocsApi: OcsApi) : GetProgramDetailsRepository {
    override suspend fun getProgramDetails(detailLink: String): ProgramDetails? =
        withContext(Dispatchers.IO) {
            ocsApi.getProgramDetails(linkUrl = OcsApi.BASE_URL + detailLink)?.toProgramDetails()
        }
}
