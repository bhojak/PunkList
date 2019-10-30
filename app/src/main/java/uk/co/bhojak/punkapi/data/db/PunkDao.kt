package uk.co.bhojak.punkapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uk.co.bhojak.punkapi.data.model.Beer


@Dao
interface PunkDao {
    @Insert
    fun insertBeer(beer: Beer)

    @Query("UPDATE BEERS SET is_favourite = :isFavourite WHERE id = :id")
    fun updateBeer(id: Int, isFavourite: Boolean): Int

    @Query("DELETE FROM beers WHERE id = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM beers WHERE id=:id LIMIT 1")
    fun getBeerById(id: Int): Beer

    @Query("SELECT * FROM beers WHERE name LIKE :query")
    fun getBeersByName(query: String): List<Beer>

    @Query("SELECT * FROM beers WHERE is_favourite = 1")
    fun getFavourites(): List<Beer>

    @Query("SELECT * FROM beers")
    fun getAllBeers(): List<Beer>

    @Query("DELETE FROM beers")
    fun deleteAllBeers()
}