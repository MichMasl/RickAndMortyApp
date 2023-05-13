package ru.mihmas.rickandmortyapp.feature_details.episode.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.mihmas.rickandmortyapp.feature_details.episode.data.model.EpisodeCharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.episode.data.model.EpisodeDetailRemote

interface EpisodeDetailApi {
    @GET("episode/{id}")
    suspend fun getEpisodeDetails(@Path("id") id: Int): EpisodeDetailRemote

    @GET("character/{list}")
    suspend fun getMultipleCharacters(@Path("list") list: String): List<EpisodeCharacterDetailRemote>
}