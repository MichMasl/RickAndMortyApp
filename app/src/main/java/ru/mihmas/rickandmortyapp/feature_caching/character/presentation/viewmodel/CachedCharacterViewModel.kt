package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.repository.CharacterCacheRepository
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.screen.*
import javax.inject.Inject

class CachedCharacterViewModel @Inject constructor(
    private val repository: CharacterCacheRepository
): ViewModel() {

    private val _state = MutableLiveData<CharacterCacheState>()
    val state: LiveData<CharacterCacheState> = _state

    fun getAllCharacters() {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.getAllCharacters()
                if (result.isEmpty()) {
                    _state.value = NoResult
                } else {
                    _state.value = Result(result = result)
                }
            } catch (e: Exception) {
                _state.value = Error
            }
        }
    }

    fun findCharacter(name: String) {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.findCharacter(name = name)
                if (result.isEmpty()) {
                    _state.value = NoResult
                } else {
                    _state.value = Result(result = result)
                }
            } catch (e: Exception) {
                _state.value = Error
            }
        }
    }

    fun filterCharacters(species: String, type: String, status: String, gender: String) {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.filterCharacters(
                    species = species,
                    type = type,
                    status = status,
                    gender = gender
                )
                if (result.isEmpty()) {
                    _state.value = NoResult
                } else {
                    _state.value = Result(result = result)
                }
            } catch (e: Exception) {
                _state.value = Error
            }
        }
    }
}