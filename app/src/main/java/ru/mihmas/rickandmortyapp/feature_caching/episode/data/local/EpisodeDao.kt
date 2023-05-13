package ru.mihmas.rickandmortyapp.feature_caching.episode.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mihmas.rickandmortyapp.feature_caching.episode.data.model.EpisodeLocal

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putListOfEpisodes(list: List<EpisodeLocal>)

    @Query("SELECT * FROM EpisodeLocal")
    suspend fun getAllEpisodes() : List<EpisodeLocal>

    @Query("SELECT * FROM EpisodeLocal WHERE name LIKE '%'||:name||'%'")
    suspend fun findEpisode(name: String) : List<EpisodeLocal>

    @Query(
        """SELECT * FROM EpisodeLocal WHERE
        (episode LIKE '%'||:episode||'%')"""
    )
    suspend fun filterEpisodes(episode: String) : List<EpisodeLocal>
}