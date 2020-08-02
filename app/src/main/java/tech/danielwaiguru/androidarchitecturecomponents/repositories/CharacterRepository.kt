package tech.danielwaiguru.androidarchitecturecomponents.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import tech.danielwaiguru.androidarchitecturecomponents.database.CharacterDao
import tech.danielwaiguru.androidarchitecturecomponents.models.Character
import tech.danielwaiguru.androidarchitecturecomponents.models.Success
import tech.danielwaiguru.androidarchitecturecomponents.networking.RemoteDataSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: CharacterDao
) {
    private val allCharacters = localDataSource.getAllCharacters()
    fun getAllCharacters(): LiveData<List<Character>> = allCharacters

    suspend fun fetchCharacters(){
        val result = remoteDataSource.getCharacters()
        if (result is Success){
            localDataSource.saveCharacters(result.data)
        }
        else{
            Log.d("API", "Server Error")
        }
    }
    suspend fun clearCharacters(){
        localDataSource.clearCharacters()
    }
}