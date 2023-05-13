package ru.mihmas.rickandmortyapp.feature_list.episode.data.model

data class EpisodeModelRemote(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)