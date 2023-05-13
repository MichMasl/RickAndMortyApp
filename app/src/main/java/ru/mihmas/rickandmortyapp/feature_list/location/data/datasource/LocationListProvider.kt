package ru.mihmas.rickandmortyapp.feature_list.location.data.datasource

import ru.mihmas.rickandmortyapp.feature_caching.location.data.local.LocationDao
import ru.mihmas.rickandmortyapp.feature_list.location.data.mapper.LocationRemoteToDomainMapper
import ru.mihmas.rickandmortyapp.feature_list.location.data.mapper.LocationRemoteToLocalMapper
import ru.mihmas.rickandmortyapp.feature_list.location.data.model.LocationModelRemote
import ru.mihmas.rickandmortyapp.feature_list.location.data.network.LocationListApi
import ru.mihmas.rickandmortyapp.feature_list.location.domain.model.LocationModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationListProvider @Inject constructor(
    private val remote: LocationListApi,
    private val dao: LocationDao
) {
    private val locationList = mutableListOf<LocationModelRemote>()
    private var nextPage: String? = DEFAULT_VALUE
    private var page = FIRST_PAGE

    suspend fun getListOfLocations(
        name: String,
        type: String,
        dimension: String
    ) : List<LocationModel> {
        if (nextPage != null) {
            val response = remote.getListOfLocations(
                page = page,
                name = name,
                type = type,
                dimension = dimension
            )
            nextPage = response.info.next
            locationList.addAll(response.results)
            page++
        }
        dao.putListOfLocations(LocationRemoteToLocalMapper(locationList).map())
        return LocationRemoteToDomainMapper(locationList).map()
    }

    fun refreshListOfLocations() {
        locationList.clear()
        nextPage = DEFAULT_VALUE
        page = FIRST_PAGE
    }

    companion object {
        private const val DEFAULT_VALUE = "defaultValue"
        private const val FIRST_PAGE = 1
    }
}