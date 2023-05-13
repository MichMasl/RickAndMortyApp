package ru.mihmas.rickandmortyapp.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.mihmas.rickandmortyapp.core.local.AppDatabase
import ru.mihmas.rickandmortyapp.feature_caching.character.data.local.CharacterDao
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.local.EpisodeDao
import ru.mihmas.rickandmortyapp.feature_caching.location.data.local.LocationDao

private const val DATABASE_NAME = "AppDatabase.db"

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context) : AppDatabase = Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    fun provideCharacterDao(database: AppDatabase) : CharacterDao = database.characterDao()

    @Provides
    fun provideEpisodeDao(database: AppDatabase) : EpisodeDao = database.episodeDao()

    @Provides
    fun provideLocationDao(database: AppDatabase) : LocationDao = database.locationDao()
}