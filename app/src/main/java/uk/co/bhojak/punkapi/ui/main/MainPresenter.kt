package uk.co.bhojak.punkapi.ui.main


import kotlinx.coroutines.*
import uk.co.bhojak.punkapi.data.model.Beer
import uk.co.bhojak.punkapi.data.repository.AppRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: AppRepository) {

    private lateinit var view: MainView

    fun setView(homeView: MainView) {
        this.view = homeView
    }

    fun loadBeerPage(currentQuery: String?, currentPage: Int, pageSize: Int) {
        GlobalScope.async (Dispatchers.IO) {
            val list = repository.getBeers(currentQuery, currentPage, pageSize)
            if (list.isNotEmpty()) {
                view.updateList(list)
            }
        }
    }

    fun checkIsBeerExistsInFavourites(beer: Beer): Boolean {
        return runBlocking {
            repository.isInFavourites(beer)
        }
    }

    fun deleteFavourite(beer: Beer) {
        GlobalScope.async (Dispatchers.IO) {
            repository.deleteFavourite(beer)
        }
    }

    fun saveFavourite(beer: Beer) {
        GlobalScope.async (Dispatchers.IO) {
            repository.saveFavourite(beer)
        }
    }

}