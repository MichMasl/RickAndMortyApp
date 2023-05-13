package ru.mihmas.rickandmortyapp.feature_list.location.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.mihmas.rickandmortyapp.feature_list.location.domain.usecase.GetListOfLocationsUseCase
import ru.mihmas.rickandmortyapp.feature_list.location.domain.usecase.RefreshListOfLocationsUseCase
import ru.mihmas.rickandmortyapp.feature_list.location.presentation.screen.*
import javax.inject.Inject

class LocationListViewModel @Inject constructor(
    private val getListOfLocationsUseCase: GetListOfLocationsUseCase,
    private val refreshListOfLocationsUseCase: RefreshListOfLocationsUseCase,
    private val locationsQuery: LocationsQuery
) : ViewModel() {

    private val _locationState = MutableLiveData<LocationState>()
    val locationState: LiveData<LocationState> = _locationState

    init {
        getListOfLocations()
    }

    fun getListOfLocations() {
        _locationState.value = Progress
        viewModelScope.launch {
            try {
                val result = getListOfLocationsUseCase.getListOfLocations(
                    name = locationsQuery.name,
                    type = locationsQuery.type,
                    dimension = locationsQuery.dimension
                )
                _locationState.value = Result(result = result)
            } catch (e: HttpException) {
                _locationState.value = NoResult
            } catch (e: Exception) {
                _locationState.value = Error
            }
        }
    }

    fun findLocationsByName(name: String) {
        refreshListOfLocationsUseCase.refreshListOfLocations()
        locationsQuery.setAllToBlank()
        locationsQuery.name = name.trim()
        getListOfLocations()
    }

    fun filterLocations(type: String, dimension: String) {
        refreshListOfLocationsUseCase.refreshListOfLocations()
        locationsQuery.setAllToBlank()
        locationsQuery.type = type.trim()
        locationsQuery.dimension = dimension.trim()
        getListOfLocations()
    }

    fun refreshListOfLocations() {
        _locationState.value = Progress
        refreshListOfLocationsUseCase.refreshListOfLocations()
        locationsQuery.setAllToBlank()
        getListOfLocations()
    }
}