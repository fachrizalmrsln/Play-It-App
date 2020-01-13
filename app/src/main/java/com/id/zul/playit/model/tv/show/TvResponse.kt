package com.id.zul.playit.model.tv.show

data class TvResponse(
    val page: Int,
    val results: MutableList<Tv>,
    val total_pages: Int,
    val total_results: Int
)