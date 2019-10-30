package uk.co.bhojak.punkapi.di

import dagger.Module
import dagger.Provides

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.bhojak.punkapi.data.api.PunkApiService
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule {

    companion object {
        private const val BASE_URL = "https://api.punkapi.com/v2/"
    }

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideLogger() : HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        .also { it.level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory() : GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providePunkService(
        @Named(BASE_URL) baseUrl: String,
        client: OkHttpClient,
        gson: GsonConverterFactory
    ): PunkApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gson)
            .build()
            .create(PunkApiService::class.java)

}