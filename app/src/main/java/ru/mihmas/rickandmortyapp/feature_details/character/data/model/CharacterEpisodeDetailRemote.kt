package ru.mihmas.rickandmortyapp.feature_details.character.data.model

data class CharacterEpisodeDetailRemote(
    val air_date: String,
    val characters: List<String>,
    val episode: String,
    val id: Int,
    val name: String,
    val created: String,
    val url: String
)