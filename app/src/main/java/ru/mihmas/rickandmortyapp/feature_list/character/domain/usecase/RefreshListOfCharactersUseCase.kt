package ru.mihmas.rickandmortyapp.feature_list.character.domain.usecase

import ru.mihmas.rickandmortyapp.feature_list.character.domain.repository.CharacterRepository
import javax.inject.Inject

class RefreshListOfCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    fun refreshBundleOfCharacters() {
        repository.refreshListOfCharacters()
    }
}