package uk.co.bhojak.punkapi.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import uk.co.bhojak.punkapi.App
import uk.co.bhojak.punkapi.R
import uk.co.bhojak.punkapi.data.model.Beer
import uk.co.bhojak.punkapi.ui.base.BaseActivity
import uk.co.bhojak.punkapi.ui.favourites.FavouritesActivity
import javax.inject.Inject

class MainActivity : MainView, BaseActivity() {

    @Inject
    lateinit var homePresenter: MainPresenter

    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.inject(this)

        homePresenter.setView(this)

        loadBeerPage()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchView = menu.findItem(R.id.home_menu_search).actionView as SearchView

        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        mSearchView.queryHint = resources.getString(R.string.search_hint)

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    mSearchView.clearFocus()
                    currentQuery = query
                    resetBeersList()
                    //mSearchView.onActionViewCollapsed()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                currentQuery = newText
                resetBeersList()
                return false
            }
        })

        val favouritesIconMenu = menu.findItem(R.id.home_menu_favorites)
        favouritesIconMenu.setOnMenuItemClickListener {

            val favoritesScreen = Intent(this, FavouritesActivity::class.java)
            startActivity(favoritesScreen)
            this@MainActivity.overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)

            false
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (currentQuery == null) {
            super.onBackPressed()
        } else {
            currentQuery = null
            resetBeersList()
        }
    }

    // VIEW METHODS

    override fun updateList(beers: List<Beer>) {
        mRecyclerView.post {
            updateRecyclerView(beers)
        }
    }

    override fun addLoadingFooter() {
        mAdapter.addLoadingFooter()
    }

    // OVERRIDE ABSTRACT METHODS

    override fun loadBeerPage() {
        homePresenter.loadBeerPage(currentQuery, currentPage, pageSize)
    }

    override fun onFavoriteItemButtonClick(beer: Beer) {
        val response = homePresenter.checkIsBeerExistsInFavourites(beer)
        if (response) {
            homePresenter.deleteFavourite(beer)
            Toast.makeText(applicationContext, getString(R.string.dislike), Toast.LENGTH_SHORT).show()
        } else {
            homePresenter.saveFavourite(beer)
            Toast.makeText(applicationContext, getString(R.string.like), Toast.LENGTH_SHORT).show()
        }
    }

    // PRIVATE METHODS

    private fun resetBeersList() {
        mAdapter.clear()

        currentPage = startPage
        isLastPage = false

        loadBeerPage()
    }

}
