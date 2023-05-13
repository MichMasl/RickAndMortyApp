package ru.mihmas.rickandmortyapp.feature_list.episode.domain.model

data class EpisodeModel(
    val id: Int,
    val name: String,
    val episode: String,
    val airDate: String
)