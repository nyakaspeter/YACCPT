package hu.gy4ez8.yaccpt.interactor.coins.event

import hu.gy4ez8.yaccpt.model.Coin

data class GetCoinsEvent (
    var code: Int = 0,
    var coins: List<Coin>? = null,
    var throwable: Throwable? = null
)