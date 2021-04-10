package hu.gy4ez8.yaccpt.ui.coins

import hu.gy4ez8.yaccpt.model.Coin

interface CoinsScreen {
    fun showCoins(coins: List<Coin>?)
    fun showNetworkError(errorMsg: String)
}