package uk.co.bhojak.punkapi.di

import android.content.Context
import dagger.Module
import dagger.Provides
import uk.co.bhojak.punkapi.data.api.PunkApiService
import uk.co.bhojak.punkapi.data.db.PunkCache
import uk.co.bhojak.punkapi.data.repository.AppRepository

import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideBrewRepository(
        context: Context,
        cache: PunkCache,
        service: PunkApiService
    ): AppRepository =
        AppRepository(context, cache, service)

}