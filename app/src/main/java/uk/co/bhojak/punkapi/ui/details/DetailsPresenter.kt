package uk.co.bhojak.punkapi.ui.details


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import uk.co.bhojak.punkapi.data.model.Beer
import uk.co.bhojak.punkapi.data.repository.AppRepository
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val repository: AppRepository) {

    private lateinit var view: DetailsView

    fun setView(detailsView: DetailsView) {
        this.view = detailsView
    }

    suspend fun checkIsBeerExistsInFavourites(beer: Beer): Boolean {
        return runBlocking {
            repository.isInFavourites(beer)
        }
    }

    suspend fun deleteFavourite(beer: Beer) {
        GlobalScope.async (Dispatchers.IO) {
            repository.deleteFavourite(beer)
        }
    }

    suspend fun saveFavourite(beer: Beer) {
        GlobalScope.async (Dispatchers.IO) {
            repository.saveFavourite(beer)
        }
    }
}