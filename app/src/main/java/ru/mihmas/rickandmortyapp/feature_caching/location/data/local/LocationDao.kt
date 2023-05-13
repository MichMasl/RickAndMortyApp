package ru.mihmas.rickandmortyapp.feature_caching.location.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mihmas.rickandmortyapp.feature_caching.location.data.model.LocationLocal

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putListOfLocations(list: List<LocationLocal>)

    @Query("SELECT * FROM LocationLocal")
    suspend fun getAllLocations() : List<LocationLocal>

    @Query("SELECT * FROM LocationLocal WHERE name LIKE '%'||:name||'%'")
    suspend fun findLocation(name: String) : List<LocationLocal>

    @Query(
        """SELECT * FROM LocationLocal WHERE
        (type LIKE '%'||:type||'%') AND
        (dimension LIKE '%'||:dimension||'%')"""
    )
    suspend fun filterLocations(type: String, dimension: String) : List<LocationLocal>
}