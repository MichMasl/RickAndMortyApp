package ru.mihmas.rickandmortyapp.feature_list.location.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mihmas.rickandmortyapp.feature_list.location.domain.usecase.GetListOfLocationsUseCase
import ru.mihmas.rickandmortyapp.feature_list.location.domain.usecase.RefreshListOfLocationsUseCase
import javax.inject.Inject

class LocationListViewModelFactory @Inject constructor(
    private val getListOfLocationsUseCase: GetListOfLocationsUseCase,
    private val refreshListOfLocationsUseCase: RefreshListOfLocationsUseCase,
    private val locationsQuery: LocationsQuery
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == LocationListViewModel::class.java) {
            return LocationListViewModel(
                getListOfLocationsUseCase = getListOfLocationsUseCase,
                refreshListOfLocationsUseCase = refreshListOfLocationsUseCase,
                locationsQuery = locationsQuery
            ) as T
        } else {
            throw RuntimeException("Unknown ViewModel class $modelClass")
        }
    }
}