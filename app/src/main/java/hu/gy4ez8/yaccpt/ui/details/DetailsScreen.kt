package hu.gy4ez8.yaccpt.ui.details

import hu.gy4ez8.yaccpt.model.Coin

interface DetailsScreen {
    fun showCoin(coin: Coin?)
    fun showNetworkError()
}