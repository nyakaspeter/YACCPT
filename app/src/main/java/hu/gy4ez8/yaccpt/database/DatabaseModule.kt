package hu.gy4ez8.yaccpt.database

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {
    @Provides
    @Singleton
    fun providesDatabase(): CoinDatabase {
        return CoinDatabase.getInstance(context)
    }
}