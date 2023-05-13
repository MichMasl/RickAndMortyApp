package ru.mihmas.rickandmortyapp.feature_list.character.data.model

data class CharacterModelRemote(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterReference,
    val name: String,
    val origin: CharacterReference,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)