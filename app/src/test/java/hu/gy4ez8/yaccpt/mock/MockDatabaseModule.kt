package hu.gy4ez8.yaccpt.mock

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import hu.gy4ez8.yaccpt.App
import hu.gy4ez8.yaccpt.database.CoinDatabase
import javax.inject.Singleton

@Module
class MockDatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(): CoinDatabase {
        return Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext() as App, CoinDatabase::class.java).allowMainThreadQueries().build()
    }
}