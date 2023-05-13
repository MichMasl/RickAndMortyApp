package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.repository.LocationCacheRepository
import javax.inject.Inject

class CachedLocationViewModelFactory @Inject constructor(
    private val repository: LocationCacheRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CachedLocationViewModel(
            repository = repository
        ) as T
    }
}