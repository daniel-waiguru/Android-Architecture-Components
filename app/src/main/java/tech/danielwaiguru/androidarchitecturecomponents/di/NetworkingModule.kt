package tech.danielwaiguru.androidarchitecturecomponents.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.danielwaiguru.androidarchitecturecomponents.networking.ApiService

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
    fun retrofitBuilder(client: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .build()
    }
    @Provides
    fun apiServiceBuilder(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}