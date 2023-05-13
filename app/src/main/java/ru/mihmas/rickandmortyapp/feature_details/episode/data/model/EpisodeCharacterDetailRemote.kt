package ru.mihmas.rickandmortyapp.feature_details.episode.data.model

data class EpisodeCharacterDetailRemote(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: EpisodeReferenceRemote,
    val name: String,
    val origin: EpisodeReferenceRemote,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)