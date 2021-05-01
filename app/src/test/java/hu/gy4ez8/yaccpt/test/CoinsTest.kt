package hu.gy4ez8.yaccpt.test

import hu.gy4ez8.yaccpt.mock.MockCoinsApi
import hu.gy4ez8.yaccpt.model.Coin
import hu.gy4ez8.yaccpt.testInjector
import hu.gy4ez8.yaccpt.ui.coins.CoinsPresenter
import hu.gy4ez8.yaccpt.ui.coins.CoinsScreen
import hu.gy4ez8.yaccpt.utils.argumentCaptor
import hu.gy4ez8.yaccpt.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class CoinsTest {
    @Inject
    lateinit var coinsPresenter: CoinsPresenter

    private lateinit var coinsScreen: CoinsScreen

    @Before
    @Throws(Exception::class)
    fun setup() {
        testInjector.inject(this)
        coinsScreen = mock()
        coinsPresenter.attachScreen(coinsScreen)
    }

    @Test
    fun testCoinsSuccess() {
        MockCoinsApi.shouldFail = false

        coinsPresenter.refreshCoins()

        val list = argumentCaptor<MutableList<Coin>>()
        Mockito.verify(coinsScreen).showCoins(list.capture())
        assert(list.value.size > 0)
    }

    @Test
    fun testCoinsFail() {
        MockCoinsApi.shouldFail = true

        coinsPresenter.refreshCoins()

        Mockito.verify(coinsScreen).showNetworkError()
    }

    @After
    fun tearDown() {
        coinsPresenter.detachScreen()
    }
}