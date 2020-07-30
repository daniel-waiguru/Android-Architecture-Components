package tech.danielwaiguru.androidarchitecturecomponents.networking

import tech.danielwaiguru.androidarchitecturecomponents.models.Character
import tech.danielwaiguru.androidarchitecturecomponents.models.Result

/**
 * Holds decoupled logic for api calls
 */
interface RemoteDataSource {
    suspend fun getCharacters(): Result<List<Character>>
    suspend fun getCharacterById(id: Int): Result<Character>
}