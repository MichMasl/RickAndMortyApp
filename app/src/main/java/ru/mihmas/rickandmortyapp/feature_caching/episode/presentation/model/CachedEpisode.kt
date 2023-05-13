package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.model

data class CachedEpisode(
    val id: Int,
    val name: String,
    val episode: String,
    val airDate: String
)