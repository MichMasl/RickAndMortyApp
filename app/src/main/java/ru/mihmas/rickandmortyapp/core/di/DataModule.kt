package ru.mihmas.rickandmortyapp.core.di

import dagger.Binds
import dagger.Module
import ru.mihmas.rickandmortyapp.feature_caching.character.data.repository.CharacterCacheRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.repository.CharacterCacheRepository
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.repository.EpisodeCacheRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.repository.EpisodeCacheRepository
import ru.mihmas.rickandmortyapp.feature_caching.location.data.repository.LocationCacheRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.repository.LocationCacheRepository
import ru.mihmas.rickandmortyapp.feature_details.character.data.repository.CharacterDetailRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_details.character.domain.repository.CharacterDetailRepository
import ru.mihmas.rickandmortyapp.feature_details.episode.data.repository.EpisodeDetailRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_details.episode.domain.repository.EpisodeDetailRepository
import ru.mihmas.rickandmortyapp.feature_details.location.data.repository.LocationDetailRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_details.location.domain.repository.LocationDetailRepository
import ru.mihmas.rickandmortyapp.feature_list.character.data.repository.CharacterRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_list.character.domain.repository.CharacterRepository
import ru.mihmas.rickandmortyapp.feature_list.episode.data.repository.EpisodeRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_list.episode.domain.repository.EpisodeRepository
import ru.mihmas.rickandmortyapp.feature_list.location.data.repository.LocationRepositoryImpl
import ru.mihmas.rickandmortyapp.feature_list.location.domain.repository.LocationRepository

@Module
interface DataModule {

    @Binds fun bindCharacterRepository(impl: CharacterRepositoryImpl) : CharacterRepository
    @Binds fun bindEpisodeRepository(impl: EpisodeRepositoryImpl) : EpisodeRepository
    @Binds fun bindLocationRepository(impl: LocationRepositoryImpl) : LocationRepository


    @Binds fun bindCharacterDetailRepository(impl: CharacterDetailRepositoryImpl) : CharacterDetailRepository
    @Binds fun bindEpisodeDetailRepository(impl: EpisodeDetailRepositoryImpl) : EpisodeDetailRepository
    @Binds fun bindLocationDetailRepository(impl: LocationDetailRepositoryImpl) : LocationDetailRepository


    @Binds fun bindCharacterCacheRepository(impl: CharacterCacheRepositoryImpl) : CharacterCacheRepository
    @Binds fun bindEpisodeCacheRepository(impl: EpisodeCacheRepositoryImpl) : EpisodeCacheRepository
    @Binds fun bindLocationCacheRepository(impl: LocationCacheRepositoryImpl) : LocationCacheRepository
}