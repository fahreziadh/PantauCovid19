package dev.ajiex.pantaucovid.data

import dev.ajiex.pantaucovid.data.model.SummaryResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("summary")
    fun getSummary(): Deferred<Response<SummaryResponse>>
}