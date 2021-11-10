package com.medhdj.data.common

import com.medhdj.data.programs.ProgramEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface OcsApi {

    @GET("contents")
    suspend fun searchPrograms(
        @Query("search") title: String,
        @Query(QUERY_PARAM_OFFSET_NAME) limit: Int,
        @Query(QUERY_PARAM_LIMIT_NAME) offset: Int
    ): SearchResponse<ProgramEntity>


    companion object {
        private const val API_VERSION = "v2"
        const val BASE_URL = "https://api.ocs.fr/apps/$API_VERSION/"
        const val STATICS_URL = "https://statics.ocs.fr"
        const val QUERY_PARAM_OFFSET_NAME = "limit"
        const val QUERY_PARAM_LIMIT_NAME = "offset"
    }
}
