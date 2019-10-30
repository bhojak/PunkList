package uk.co.bhojak.punkapi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uk.co.bhojak.punkapi.data.db.PunkCache
import uk.co.bhojak.punkapi.data.db.PunkDao
import uk.co.bhojak.punkapi.data.db.PunkDatabase

import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(app: Context): PunkDatabase {
        return Room.databaseBuilder(app, PunkDatabase::class.java, "PUNK.DB").build()
    }

    @Singleton
    @Provides
    fun providesPunkDao(demoDatabase: PunkDatabase): PunkDao {
        return demoDatabase.getPunkDao()
    }

    @Provides
    @Singleton
    fun providePunkCache(dao: PunkDao): PunkCache = PunkCache(dao)

}