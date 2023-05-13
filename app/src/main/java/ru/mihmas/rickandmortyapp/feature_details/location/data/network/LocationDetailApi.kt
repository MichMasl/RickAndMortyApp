package ru.mihmas.rickandmortyapp.feature_details.location.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.mihmas.rickandmortyapp.feature_details.location.data.model.LocationCharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.location.data.model.LocationDetailRemote

interface LocationDetailApi {
    @GET("location/{id}")
    fun getLocationDetails(@Path("id") id: Int) : Single<LocationDetailRemote>

    @GET("character/{list}")
    fun getMultipleCharacters(@Path("list") list: String): Single<List<LocationCharacterDetailRemote>>
}