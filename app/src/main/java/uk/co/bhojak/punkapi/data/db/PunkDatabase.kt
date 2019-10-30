package uk.co.bhojak.punkapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.co.bhojak.punkapi.data.model.Beer

@Database(
    entities = [Beer::class],
    version = 1,
    exportSchema = false
)
abstract class PunkDatabase : RoomDatabase() {

    abstract fun getPunkDao() : PunkDao

}