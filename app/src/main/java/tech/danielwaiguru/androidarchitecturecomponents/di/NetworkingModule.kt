package tech.danielwaiguru.androidarchitecturecomponents.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.danielwaiguru.androidarchitecturecomponents.CharacterDao
import tech.danielwaiguru.androidarchitecturecomponents.database.CharacterDatabase
import tech.danielwaiguru.androidarchitecturecomponents.networking.ApiService
import tech.danielwaiguru.androidarchitecturecomponents.networking.RemoteDataSource
import tech.danielwaiguru.androidarchitecturecomponents.networking.RemoteDataSourceImpl
import tech.danielwaiguru.androidarchitecturecomponents.repositories.CharacterRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkingModule {
    private const val BASE_URL = "https://rickandmortyapi.com"
    @Provides
    fun provideConverter() : Converter.Factory = MoshiConverterFactory.create()
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    @Provides
    @Singleton
    fun retrofitBuilder(client: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .build()
    }
    @Provides
    @Singleton
    fun apiServiceBuilder(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService):RemoteDataSource =
        RemoteDataSourceImpl(apiService)
    @Provides
    @Singleton
    fun characterDatabase(@ApplicationContext context: Context) =
        CharacterDatabase.getDatabaseInstance(context)
    @Provides
    @Singleton
    fun characterDao(database: CharacterDatabase) =
        database.characterDao()
    @Provides
    @Singleton
    fun repository(remoteDataSource: RemoteDataSourceImpl,
    localDataSource: CharacterDao): CharacterRepository =
        CharacterRepository(remoteDataSource, localDataSource)

}