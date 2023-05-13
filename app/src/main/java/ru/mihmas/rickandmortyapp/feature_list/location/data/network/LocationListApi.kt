package ru.mihmas.rickandmortyapp.feature_list.location.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mihmas.rickandmortyapp.feature_list.location.data.model.LocationRemoteResponse

interface LocationListApi {
    @GET("location/")
    suspend fun getListOfLocations(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("dimension") dimension: String,
    ) : LocationRemoteResponse
}