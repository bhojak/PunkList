package uk.co.bhojak.punkapi.ui.favourites


import kotlinx.coroutines.*
import uk.co.bhojak.punkapi.data.model.Beer
import uk.co.bhojak.punkapi.data.repository.AppRepository
import javax.inject.Inject

class FavouritesPresenter @Inject constructor(private val repository: AppRepository) {

    private lateinit var view: FavouritesView

    fun setView(favouritesView: FavouritesView) {
        this.view = favouritesView
    }

    fun loadFavouritesPage(currentPage: Int, pageSize: Int) {
        GlobalScope.async {
            val list = repository.getFavourites(currentPage, pageSize)
            view.updateList(list)
        }
    }

    fun deleteBeer(beer: Beer) {
        GlobalScope.async {
            repository.deleteFavourite(beer)
        }
    }

}