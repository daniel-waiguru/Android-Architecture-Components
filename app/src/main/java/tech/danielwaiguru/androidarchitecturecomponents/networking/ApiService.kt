package tech.danielwaiguru.androidarchitecturecomponents.networking

import retrofit2.http.GET
import retrofit2.http.Path
import tech.danielwaiguru.androidarchitecturecomponents.models.Character
import tech.danielwaiguru.androidarchitecturecomponents.models.response.CharactersResponse

/**
 * retrofit powered api calls
 */
interface ApiService {
    @GET("/api/character")
    suspend fun getCharacters(): CharactersResponse
    @GET("/api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character
}