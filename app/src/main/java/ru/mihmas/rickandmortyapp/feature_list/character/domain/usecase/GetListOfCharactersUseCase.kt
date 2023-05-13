package ru.mihmas.rickandmortyapp.feature_list.character.domain.usecase

import ru.mihmas.rickandmortyapp.feature_list.character.domain.model.CharacterModel
import ru.mihmas.rickandmortyapp.feature_list.character.domain.repository.CharacterRepository
import javax.inject.Inject

class GetListOfCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend fun getListOfCharacters(
        name: String,
        species: String,
        type: String,
        status: String,
        gender: String
    ) : List<CharacterModel> {
        return repository.getListOfCharacters(
            name, species, type, status, gender
        )
    }
}