package ru.mihmas.rickandmortyapp.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mihmas.rickandmortyapp.feature_caching.character.data.local.CharacterDao
import ru.mihmas.rickandmortyapp.feature_caching.character.data.model.CharacterLocal
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.local.EpisodeDao
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.model.EpisodeLocal
import ru.mihmas.rickandmortyapp.feature_caching.location.data.local.LocationDao
import ru.mihmas.rickandmortyapp.feature_caching.location.data.model.LocationLocal

@Database(
    entities = [CharacterLocal::class, EpisodeLocal::class, LocationLocal::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao
    abstract fun episodeDao() : EpisodeDao
    abstract fun locationDao() : LocationDao
}