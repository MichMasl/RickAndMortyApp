package ru.mihmas.rickandmortyapp.feature_details.character.domain.model

data class CharacterEpisodeDetail(
    val id: Int,
    val name: String,
    val episode: String,
    val airDate: String
)