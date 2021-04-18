package hu.gy4ez8.yaccpt.database

import androidx.room.*
import hu.gy4ez8.yaccpt.model.Coin

@Dao
interface CoinDAO {
    @Query ("SELECT * FROM coin")
    fun getAllCoins(): List<Coin>
    @Query("SELECT * FROM coin WHERE coin.id = :id")
    fun getCoin(id: String): Coin
    @Insert
    fun insertCoin(coin: Coin)
    @Update
    fun updateCoin(coin: Coin)
    @Delete
    fun deleteCoin(coin: Coin)
    @Query("DELETE FROM coin")
    fun deleteAllCoins()
}