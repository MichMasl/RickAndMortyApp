package ru.mihmas.rickandmortyapp.feature_list.character.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String,
)