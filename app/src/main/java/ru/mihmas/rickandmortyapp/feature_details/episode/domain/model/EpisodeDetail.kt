package ru.mihmas.rickandmortyapp.feature_details.episode.domain.model

data class EpisodeDetail(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>
)