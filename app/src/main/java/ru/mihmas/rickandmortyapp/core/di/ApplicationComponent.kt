package ru.mihmas.rickandmortyapp.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.mihmas.rickandmortyapp.feature_caching.character.presentation.screen.CharacterLocalListFragment
import ru.mihmas.rickandmortyapp.feature_caching.episode.presentation.screen.EpisodeLocalListFragment
import ru.mihmas.rickandmortyapp.feature_caching.location.presentation.screen.LocationLocalListFragment
import ru.mihmas.rickandmortyapp.feature_details.character.presentation.screen.CharacterDetailFragment
import ru.mihmas.rickandmortyapp.feature_details.episode.presentation.screen.EpisodeDetailFragment
import ru.mihmas.rickandmortyapp.feature_details.location.presentation.screen.LocationDetailFragment
import ru.mihmas.rickandmortyapp.feature_list.character.presentation.screen.CharacterRemoteListFragment
import ru.mihmas.rickandmortyapp.feature_list.episode.presentation.screen.EpisodeRemoteListFragment
import ru.mihmas.rickandmortyapp.feature_list.location.presentation.screen.LocationRemoteListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    DatabaseModule::class,
    DataModule::class
])
interface ApplicationComponent {
    fun inject(fragment: CharacterRemoteListFragment)
    fun inject(fragment: EpisodeRemoteListFragment)
    fun inject(fragment: LocationRemoteListFragment)

    fun inject(fragment: CharacterDetailFragment)
    fun inject(fragment: EpisodeDetailFragment)
    fun inject(fragment: LocationDetailFragment)

    fun inject(fragment: CharacterLocalListFragment)
    fun inject(fragment: EpisodeLocalListFragment)
    fun inject(fragment: LocationLocalListFragment)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}