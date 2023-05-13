package ru.mihmas.rickandmortyapp.feature_list.character.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mihmas.rickandmortyapp.feature_list.character.data.model.CharacterRemoteResponse

interface CharacterListApi {
    @GET("character/")
    suspend fun getListOfCharacters(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("species") species: String,
        @Query("type") type: String,
        @Query("status") status: String,
        @Query("gender") gender: String,
    ) : CharacterRemoteResponse
}