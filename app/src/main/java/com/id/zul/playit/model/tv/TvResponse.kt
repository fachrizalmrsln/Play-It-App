package com.id.zul.playit.model.tv

data class TvResponse(
    val page: Int,
    val results: MutableList<Tv>,
    val total_pages: Int,
    val total_results: Int
)