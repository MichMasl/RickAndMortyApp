package ru.mihmas.rickandmortyapp.feature_list.episode.data.model

data class EpisodeResponseInfo(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)