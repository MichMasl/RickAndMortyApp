package ru.mihmas.rickandmortyapp.feature_list.location.presentation.screen

import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel
import javax.inject.Inject

sealed class LocationState

object Progress : LocationState()
object Error : LocationState()
object NoResult : LocationState()
class Result @Inject constructor(val result: List<LocationModel>) : LocationState()