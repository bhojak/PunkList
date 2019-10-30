package uk.co.bhojak.punkapi.ui.main

import uk.co.bhojak.punkapi.data.model.Beer

interface MainView {

    fun updateList(beers: List<Beer>)
    fun addLoadingFooter()
}