package ru.mihmas.rickandmortyapp.feature_caching.character.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mihmas.rickandmortyapp.feature_caching.character.data.model.CharacterLocal

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putListOfCharacters(listOfCharacters: List<CharacterLocal>)

    @Query("SELECT * FROM CharacterLocal")
    suspend fun getAllCharacters() : List<CharacterLocal>

    @Query("SELECT * FROM CharacterLocal WHERE name LIKE '%'||:name||'%'")
    suspend fun findCharacter(name: String) : List<CharacterLocal>

    @Query(
        """SELECT * FROM CharacterLocal WHERE
        (species LIKE '%'||:species||'%') AND
        (type LIKE '%'||:type||'%') AND
        (status LIKE '%'||:status||'%') AND
        (gender LIKE '%'||:gender||'%')"""
    )
    suspend fun filterCharacters(species: String, type: String, status: String, gender: String) : List<CharacterLocal>
}