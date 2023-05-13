package ru.mihmas.rickandmortyapp.feature_details.character.data.model

data class CharacterDetailRemote(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterReferenceRemote,
    val name: String,
    val origin: CharacterReferenceRemote,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)