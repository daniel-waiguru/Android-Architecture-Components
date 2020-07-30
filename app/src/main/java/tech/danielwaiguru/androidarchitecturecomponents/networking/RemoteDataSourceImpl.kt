package tech.danielwaiguru.androidarchitecturecomponents.networking

import tech.danielwaiguru.androidarchitecturecomponents.models.Character
import tech.danielwaiguru.androidarchitecturecomponents.models.Failure
import tech.danielwaiguru.androidarchitecturecomponents.models.Result
import tech.danielwaiguru.androidarchitecturecomponents.models.Success
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): RemoteDataSource {
    override suspend fun getCharacters(): Result<List<Character>> =
        try {
            val result = apiService.getCharacters()
            Success(result.characters)
        }
        catch (error: Throwable){
            Failure(error)
        }
    override suspend fun getCharacterById(id: Int): Result<Character> =
        try {
            val result = apiService.getCharacterById(id)
            Success(result)
        }
        catch (error: Throwable){
            Failure(error)
        }
}