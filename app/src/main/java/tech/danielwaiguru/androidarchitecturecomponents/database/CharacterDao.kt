package tech.danielwaiguru.androidarchitecturecomponents.database

import androidx.lifecycle.LiveData
import androidx.room.*
import tech.danielwaiguru.androidarchitecturecomponents.models.Character

@Dao
interface CharacterDao {
    /**
     * Queries to access the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<Character>)
    @Delete
    suspend fun deleteCharacter(character: Character)
    @Query("DELETE FROM character")
    suspend fun clearCharacters()
    @Query("SELECT * FROM character")
    fun getAllCharacters(): LiveData<List<Character>>
}