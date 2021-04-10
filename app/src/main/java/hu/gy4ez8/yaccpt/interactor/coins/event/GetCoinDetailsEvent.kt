package hu.gy4ez8.yaccpt.interactor.coins.event

import hu.gy4ez8.yaccpt.model.Coin

data class GetCoinDetailsEvent (
    var code: Int = 0,
    var coin: Coin? = null,
    var throwable: Throwable? = null
)