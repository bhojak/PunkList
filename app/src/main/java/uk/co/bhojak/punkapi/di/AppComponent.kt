package uk.co.bhojak.punkapi.di

import dagger.Component
import uk.co.bhojak.punkapi.ui.details.DetailsActivity
import uk.co.bhojak.punkapi.ui.favourites.FavouritesActivity
import uk.co.bhojak.punkapi.ui.main.MainActivity

import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    PresenterModule::class,
    DatabaseModule::class,
    ServiceModule::class,
    RepositoryModule::class])
interface AppComponent {


    fun inject(target: MainActivity)
    fun inject(target: FavouritesActivity)
    fun inject(target: DetailsActivity)

}