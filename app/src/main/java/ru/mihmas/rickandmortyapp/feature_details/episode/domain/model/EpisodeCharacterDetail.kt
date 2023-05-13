package ru.mihmas.rickandmortyapp.feature_details.episode.domain.model

data class EpisodeCharacterDetail(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String,
)
