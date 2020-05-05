package dev.ajiex.pantaucovid.data.repository

import dev.ajiex.pantaucovid.data.Api
import dev.ajiex.pantaucovid.data.model.SummaryResponse
import retrofit2.Response

class SummaryRepository(private val api: Api) {

    suspend fun getSummary(): Response<SummaryResponse> {
        val res = api.getSummary().await()
        return res
    }
}