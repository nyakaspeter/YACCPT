package hu.gy4ez8.yaccpt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.gy4ez8.yaccpt.model.Coin

@Database(entities = [Coin::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDAO
    companion object {
        private var INSTANCE: CoinDatabase? = null
        fun getInstance(context: Context): CoinDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    CoinDatabase::class.java, "coin.db").build()
            }
            return INSTANCE!!
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}