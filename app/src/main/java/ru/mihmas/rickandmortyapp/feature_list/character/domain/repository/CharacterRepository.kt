package ru.mihmas.rickandmortyapp.feature_list.character.domain.repository

import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel

interface CharacterRepository {
    suspend fun getListOfCharacters(
        name: String,
        species: String,
        type: String,
        status: String,
        gender: String
    ) : List<CharacterModel>
    fun refreshListOfCharacters()
}