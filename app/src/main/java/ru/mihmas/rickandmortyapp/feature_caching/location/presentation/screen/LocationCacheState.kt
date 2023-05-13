package ru.mihmas.rickandmortyapp.feature_caching.location.presentation.screen

import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.model.CachedLocation
import javax.inject.Inject

sealed class LocationCacheState

object Progress : LocationCacheState()
object Error : LocationCacheState()
object NoResult : LocationCacheState()
class Result @Inject constructor(val result: List<CachedLocation>) : LocationCacheState()