package ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.repository.EpisodeCacheRepository
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.screen.*
import javax.inject.Inject

class CachedEpisodeViewModel @Inject constructor(
    private val repository: EpisodeCacheRepository
): ViewModel() {

    private val _state = MutableLiveData<EpisodeCacheState>()
    val state: LiveData<EpisodeCacheState> = _state

    fun getAllEpisodes() {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.getAllEpisodes()
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

    fun findEpisode(name: String) {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.findEpisode(name = name)
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

    fun filterEpisodes(episode: String) {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.filterEpisodes(episode)
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