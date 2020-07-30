package tech.danielwaiguru.androidarchitecturecomponents.repositories

import tech.danielwaiguru.androidarchitecturecomponents.CharacterDao
import tech.danielwaiguru.androidarchitecturecomponents.networking.RemoteDataSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: CharacterDao
) {
}