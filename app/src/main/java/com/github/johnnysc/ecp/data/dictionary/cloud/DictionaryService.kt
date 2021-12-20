package com.github.johnnysc.ecp.data.dictionary.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryService {

    @GET("entries/{lang}/{word}")
    suspend fun word(@Path("lang") lang: String, @Path("word") word: String): ResponseBody
}