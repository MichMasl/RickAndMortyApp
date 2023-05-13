package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.repository.EpisodeCacheRepository
import javax.inject.Inject

class CachedEpisodeViewModelFactory @Inject constructor(
    private val repository: EpisodeCacheRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CachedEpisodeViewModel(
            repository = repository
        ) as T
    }
}