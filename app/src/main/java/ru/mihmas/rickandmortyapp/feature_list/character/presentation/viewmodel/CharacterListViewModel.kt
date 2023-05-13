package ru.mihmas.rickandmortyapp.feature_list.character.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.mihmas.rickandmortyapp.feature_list.character.domain.usecase.GetListOfCharactersUseCase
import ru.mihmas.rickandmortyapp.feature_list.character.domain.usecase.RefreshListOfCharactersUseCase
import ru.mihmas.rickandmortyapp.feature_list.character.presentation.screen.*
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val refreshListOfCharactersUseCase: RefreshListOfCharactersUseCase,
    private val getListOfCharactersUseCase: GetListOfCharactersUseCase,
    private val charactersQuery: CharactersQuery
): ViewModel() {
    
    private val _characterState = MutableLiveData<CharacterState>()
    val characterState: LiveData<CharacterState> = _characterState

    init {
        getListOfCharacters()
    }

    fun getListOfCharacters() {
        _characterState.value = Progress
        viewModelScope.launch {
            try {
                val result = getListOfCharactersUseCase.getListOfCharacters(
                    name = charactersQuery.name,
                    species = charactersQuery.species,
                    type = charactersQuery.type,
                    status = charactersQuery.status,
                    gender = charactersQuery.gender
                )
                _characterState.value = Result(result = result)
            } catch (e: HttpException) {
                _characterState.value = NoResult
            } catch (e: Exception) {
                _characterState.value = Error
            }
        }
    }

    fun findCharactersByName(name: String) {
        refreshListOfCharactersUseCase.refreshBundleOfCharacters()
        charactersQuery.setAllToBlank()
        charactersQuery.name = name.trim()
        getListOfCharacters()
    }

    fun filterCharacters(species: String, type: String, status: String, gender: String) {
        refreshListOfCharactersUseCase.refreshBundleOfCharacters()
        charactersQuery.setAllToBlank()
        charactersQuery.species = species.trim()
        charactersQuery.type = type.trim()
        charactersQuery.status = status.trim()
        charactersQuery.gender = gender.trim()
        getListOfCharacters()
    }

    fun refreshListOfCharacters() {
        _characterState.value = Progress
        refreshListOfCharactersUseCase.refreshBundleOfCharacters()
        charactersQuery.setAllToBlank()
        getListOfCharacters()
    }
}