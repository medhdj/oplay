package com.medhdj.data.common

import com.medhdj.data.programs.ProgramDetailsEntity
import com.medhdj.data.programs.ProgramEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface OcsApi {

    @GET("contents")
    suspend fun searchPrograms(
        @Query("search") title: String,
        @Query(QUERY_PARAM_LIMIT_NAME) limit: Int,
        @Query(QUERY_PARAM_OFFSET_NAME) offset: Int
    ): SearchResponse<ProgramEntity>

    @GET
    suspend fun getProgramDetails(
        @Url linkUrl: String
    ): ProgramDetailsEntity

    companion object {

        private const val API_VERSION = "v2"
        const val BASE_URL = "https://api.ocs.fr"
        const val DEFAULT_API_URL = "$BASE_URL/apps/$API_VERSION/"
        const val STATICS_URL = "https://statics.ocs.fr"
        const val QUERY_PARAM_OFFSET_NAME = "offset"
        const val QUERY_PARAM_LIMIT_NAME = "limit"
    }
}
