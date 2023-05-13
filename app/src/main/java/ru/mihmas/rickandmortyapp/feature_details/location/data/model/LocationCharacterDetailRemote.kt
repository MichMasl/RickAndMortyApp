package ru.mihmas.rickandmortyapp.feature_details.location.data.model

data class LocationCharacterDetailRemote(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationReferenceRemote,
    val location: LocationReferenceRemote,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String

)