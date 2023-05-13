package ru.mihmas.rickandmortyapp.feature_details.character.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.mihmas.rickandmortyapp.feature_details.character.data.model.CharacterDetailRemote
import ru.mihmas.rickandmortyapp.feature_details.character.data.model.CharacterEpisodeDetailRemote

interface CharacterDetailApi {
    @GET("character/{id}")
    fun getCharacterDetails(@Path("id") id: Int): Single<CharacterDetailRemote>

    @GET("episode/{list}")
    fun getMultipleEpisodes(@Path("list") list: String): Single<List<CharacterEpisodeDetailRemote>>
}