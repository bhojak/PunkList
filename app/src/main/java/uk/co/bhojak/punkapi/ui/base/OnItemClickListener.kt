package uk.co.bhojak.punkapi.ui.base


import uk.co.bhojak.punkapi.data.model.Beer

interface OnItemClickListener {
    fun onItemClick(beer: Beer)
    fun onFavoritesButtonClick(beer: Beer)
}