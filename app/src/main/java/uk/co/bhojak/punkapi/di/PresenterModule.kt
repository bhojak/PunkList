package uk.co.bhojak.punkapi.di

import dagger.Module
import dagger.Provides
import uk.co.bhojak.punkapi.data.repository.AppRepository
import uk.co.bhojak.punkapi.ui.details.DetailsPresenter
import uk.co.bhojak.punkapi.ui.favourites.FavouritesPresenter
import uk.co.bhojak.punkapi.ui.main.MainPresenter

import javax.inject.Singleton

@Module
class PresenterModule {



    @Provides
    @Singleton
    fun provideHomePresenter(repository: AppRepository): MainPresenter = MainPresenter(repository)

    @Provides
    @Singleton
    fun provideFavouritesPresenter(repository: AppRepository): FavouritesPresenter = FavouritesPresenter(repository)

    @Provides
    @Singleton
    fun provideDetailsPresenter(repository: AppRepository): DetailsPresenter = DetailsPresenter(repository)

}