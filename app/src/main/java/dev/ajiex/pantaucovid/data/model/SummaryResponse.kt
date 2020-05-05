package dev.ajiex.pantaucovid.data.model

data class SummaryResponse(
    val Countries: List<Country>?,
    val Date: String?,
    val Global: Global?
)