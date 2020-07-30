package tech.danielwaiguru.androidarchitecturecomponents.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import tech.danielwaiguru.androidarchitecturecomponents.networking.RemoteDataSource
import tech.danielwaiguru.androidarchitecturecomponents.networking.RemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl):RemoteDataSource
}