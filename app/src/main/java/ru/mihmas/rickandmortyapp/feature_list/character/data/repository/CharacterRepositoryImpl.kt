package ru.mihmas.rickandmortyapp.feature_list.character.data.repository

import ru.mihmas.rickandmortyapp.feature_list.character.data.datasource.CharacterListProvider
import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel
import ru.mihmas.rickandmortyapp.feature_list.character.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterListProvider: CharacterListProvider
) : CharacterRepository {
    override suspend fun getListOfCharacters(
        name: String,
        species: String,
        type: String,
        status: String,
        gender: String
    ): List<CharacterModel> {
        return characterListProvider.getListOfCharacters(
            name = name,
            species = species,
            type = type,
            status = status,
            gender = gender
        )
    }
    override fun refreshListOfCharacters() {
        characterListProvider.refreshListOfCharacters()
    }
}