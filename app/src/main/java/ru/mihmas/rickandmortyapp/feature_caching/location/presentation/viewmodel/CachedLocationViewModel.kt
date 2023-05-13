package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.repository.LocationCacheRepository
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.screen.*
import javax.inject.Inject

class CachedLocationViewModel @Inject constructor(
    private val repository: LocationCacheRepository
): ViewModel() {

    private val _state = MutableLiveData<LocationCacheState>()
    val state: LiveData<LocationCacheState> = _state

    fun getAllLocations() {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.getAllLocations()
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

    fun findLocation(name: String) {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.findLocation(name = name)
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

    fun filterLocations(type: String, dimension: String) {
        _state.value = Progress
        viewModelScope.launch {
            try {
                val result = repository.filterLocations(
                    type = type,
                    dimension = dimension
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