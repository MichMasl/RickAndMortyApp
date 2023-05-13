package ru.mihmas.rickandmortyapp.feature_list.character.data.model

data class CharacterResponseInfo(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)