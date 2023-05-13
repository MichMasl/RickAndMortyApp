package ru.mihmas.rickandmortyapp.feature_caching.character.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.repository.CharacterCacheRepository
import javax.inject.Inject

class CachedCharacterViewModelFactory @Inject constructor(
    private val repository: CharacterCacheRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CachedCharacterViewModel(
            repository = repository
        ) as T
    }
}