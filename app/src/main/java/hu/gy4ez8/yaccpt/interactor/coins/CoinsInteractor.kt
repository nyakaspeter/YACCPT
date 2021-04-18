package hu.gy4ez8.yaccpt.interactor.coins

import android.util.Log
import hu.gy4ez8.yaccpt.interactor.coins.event.GetCoinDetailsEvent
import hu.gy4ez8.yaccpt.interactor.coins.event.GetCoinsEvent
import hu.gy4ez8.yaccpt.network.CoinsApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class CoinsInteractor @Inject constructor(private var coinsApi: CoinsApi){
    fun getCoins() {
        val event = GetCoinsEvent()

        try {
            val coinsCall = coinsApi.getCoins()

            val response = coinsCall.execute()
            Log.d("Reponse", response.body().toString())
            if (response.code() != 200) {
                throw Exception("Result code is not 200")
            }
            event.code = response.code()
            event.coins = response.body()?.data
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun getCoinDetails(id: String) {
        val event = GetCoinDetailsEvent()

        try {
            val coinDetailsQueryCall = coinsApi.getCoin(id)

            val response = coinDetailsQueryCall.execute()
            Log.d("Reponse", response.body().toString())
            if (response.code() != 200) {
                throw Exception("Result code is not 200")
            }
            event.code = response.code()
            event.coin = response.body()?.get(0)
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }
}