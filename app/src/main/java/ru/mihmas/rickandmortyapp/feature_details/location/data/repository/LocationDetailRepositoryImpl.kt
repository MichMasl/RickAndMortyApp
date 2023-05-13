package ru.mihmas.rickandmortyapp.feature_details.location.data.repository

import io.reactivex.rxjava3.core.Single
import ru.mihmas.rickandmortyapp.feature_details.location.data.mapper.LocationDetailMapper
import ru.mihmas.rickandmortyapp.feature_details.location.data.network.LocationDetailApi
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationCharacterDetail
import ru.mihmas.rickandmortyapp.feature_details.location.domain.model.LocationDetail
import ru.mihmas.rickandmortyapp.feature_details.location.domain.repository.LocationDetailRepository
import javax.inject.Inject

class LocationDetailRepositoryImpl @Inject constructor(
    private val api: LocationDetailApi
) : LocationDetailRepository {
    override fun getLocationDetails(id: Int): Single<LocationDetail> {
        return api.getLocationDetails(id = id).map {
            LocationDetailMapper.mapRemoteToDomain(it)
        }
    }

    override fun getMultipleCharacters(list: String): Single<List<LocationCharacterDetail>> {
        return api.getMultipleCharacters(list = list).map {
            LocationDetailMapper.characterListRemoteToDomain(it)
        }
    }
}