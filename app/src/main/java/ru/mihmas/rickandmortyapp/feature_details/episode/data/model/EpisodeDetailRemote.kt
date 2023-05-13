package ru.mihmas.rickandmortyapp.feature_details.episode.data.model

data class EpisodeDetailRemote(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)