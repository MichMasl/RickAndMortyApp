package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.model

data class CachedCharacter(
    val id: Int,
    val name: String,
    val species: String,
    val type: String,
    val status: String,
    val gender: String,
    val image: String
)