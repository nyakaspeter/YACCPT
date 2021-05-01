package hu.gy4ez8.yaccpt.mock

import hu.gy4ez8.yaccpt.model.Coin
import hu.gy4ez8.yaccpt.model.CoinResponse
import hu.gy4ez8.yaccpt.model.CoinsResponse
import hu.gy4ez8.yaccpt.model.CoinsResponseInfo
import hu.gy4ez8.yaccpt.network.CoinsApi
import hu.gy4ez8.yaccpt.utils.mock
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.math.BigDecimal

class MockCoinsApi : CoinsApi {
    companion object {
        var shouldFail = false
    }

    private var coins: MutableList<Coin> = mutableListOf(
        Coin("1", "btc", "bitcoin"),
        Coin("2", "eth", "ethereum"),
        Coin("3", "xrp", "ripple")
    )

    override fun addCoin(coin: Coin?): Call<Coin> {
        coins.add(coin!!)

        return object : Call<Coin> {
            @Throws(IOException::class)
            override fun execute(): Response<Coin> {
                return Response.success(coin)
            }

            override fun enqueue(callback: Callback<Coin>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<Coin> {
                return this
            }

            override fun request(): Request? {
                return null
            }

            override fun timeout(): Timeout {
                return Timeout()
            }
        }
    }

    override fun deleteCoin(id: String?): Call<Void> {
        val coin = coins.find { it.id == id }
        coins.remove(coin!!)

        return object : Call<Void> {
            @Throws(IOException::class)
            override fun execute(): Response<Void> {
                return Response.success(null)
            }

            override fun enqueue(callback: Callback<Void>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<Void> {
                return this
            }

            override fun request(): Request? {
                return null
            }

            override fun timeout(): Timeout {
                return Timeout()
            }
        }
    }

    override fun getCoin(id: String?): Call<CoinResponse> {
        val coin = coins.find { it.id == id }
        val response = listOf(coin!!)

        return object : Call<CoinResponse> {
            @Throws(IOException::class)
            override fun execute(): Response<CoinResponse?> {
                return if (!shouldFail) Response.success(response) else Response.error(500, mock())
            }

            override fun enqueue(callback: Callback<CoinResponse>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<CoinResponse> {
                return this
            }

            override fun request(): Request? {
                return null
            }

            override fun timeout(): Timeout {
                return Timeout()
            }
        }
    }

    override fun getCoins(): Call<CoinsResponse> {
        val response = CoinsResponse(
            coins, CoinsResponseInfo(
                BigDecimal(coins.size), BigDecimal(
                    1234
                )
            )
        )

        return object : Call<CoinsResponse> {
            @Throws(IOException::class)
            override fun execute(): Response<CoinsResponse?> {
                return if (!shouldFail) Response.success(response) else Response.error(500, mock())
            }

            override fun enqueue(callback: Callback<CoinsResponse>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<CoinsResponse> {
                return this
            }

            override fun request(): Request? {
                return null
            }

            override fun timeout(): Timeout {
                return Timeout()
            }
        }
    }

    override fun updateCoin(id: String?, coin: Coin?): Call<Coin> {
        val coinToUpdate = coins.find { it.id == id }
        coins.remove(coinToUpdate!!)
        coins.add(coin!!)

        return object : Call<Coin> {
            @Throws(IOException::class)
            override fun execute(): Response<Coin> {
                return Response.success(coin)
            }

            override fun enqueue(callback: Callback<Coin>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<Coin> {
                return this
            }

            override fun request(): Request? {
                return null
            }

            override fun timeout(): Timeout {
                return Timeout()
            }
        }
    }
}