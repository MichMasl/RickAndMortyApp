package ru.mihmas.rickandmortyapp.feature_list.episode.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mihmas.rickandmortyapp.feature_list.episode.data.model.EpisodeRemoteResponse

interface EpisodeListApi {
    @GET("episode/")
    suspend fun getListOfEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("episode") episode: String,
    ): EpisodeRemoteResponse
}