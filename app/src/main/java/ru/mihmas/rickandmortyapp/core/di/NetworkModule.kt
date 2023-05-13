package ru.mihmas.rickandmortyapp.core.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.mihmas.rickandmortyapp.feature_details.character.data.network.CharacterDetailApi
import ru.mihmas.rickandmortyapp.feature_details.episode.data.network.EpisodeDetailApi
import ru.mihmas.rickandmortyapp.feature_details.location.data.network.LocationDetailApi
import ru.mihmas.rickandmortyapp.feature_list.character.data.remote.CharacterListApi
import ru.mihmas.rickandmortyapp.feature_list.episode.data.network.EpisodeListApi
import ru.mihmas.rickandmortyapp.feature_list.location.data.network.LocationListApi
import javax.inject.Singleton

private const val BASE_URL = "https://rickandmortyapi.com/api/"

@Module
class NetworkModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()



    @Provides fun provideCharacterListApi(retrofit: Retrofit) : CharacterListApi = retrofit.create(CharacterListApi::class.java)
    @Provides fun provideEpisodeListApi(retrofit: Retrofit) : EpisodeListApi = retrofit.create(EpisodeListApi::class.java)
    @Provides fun provideLocationListApi(retrofit: Retrofit) : LocationListApi = retrofit.create(LocationListApi::class.java)


    @Provides fun provideCharacterDetailApi(retrofit: Retrofit) : CharacterDetailApi = retrofit.create(CharacterDetailApi::class.java)
    @Provides fun provideEpisodeDetailApi(retrofit: Retrofit) : EpisodeDetailApi = retrofit.create(EpisodeDetailApi::class.java)
    @Provides fun provideLocationDetailApi(retrofit: Retrofit) : LocationDetailApi = retrofit.create(LocationDetailApi::class.java)
}