package uk.co.bhojak.punkapi.ui.favourites

import uk.co.bhojak.punkapi.data.model.Beer


interface FavouritesView {
    fun updateList(beers: List<Beer>)
    fun addLoadingFooter()
}