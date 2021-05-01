package hu.gy4ez8.yaccpt.test

import hu.gy4ez8.yaccpt.mock.MockCoinsApi
import hu.gy4ez8.yaccpt.model.Coin
import hu.gy4ez8.yaccpt.testInjector
import hu.gy4ez8.yaccpt.ui.details.DetailsPresenter
import hu.gy4ez8.yaccpt.ui.details.DetailsScreen
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
class DetailsTest {
    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    private lateinit var detailsScreen: DetailsScreen

    @Before
    @Throws(Exception::class)
    fun setup() {
        testInjector.inject(this)
        detailsScreen = mock()
        detailsPresenter.attachScreen(detailsScreen)
    }

    @Test
    fun testDetailsSuccess() {
        MockCoinsApi.shouldFail = false

        detailsPresenter.refreshCoin("1")

        val coin = argumentCaptor<Coin>()
        Mockito.verify(detailsScreen).showCoin(coin.capture())
    }

    @Test
    fun testDetailsFail() {
        MockCoinsApi.shouldFail = true

        detailsPresenter.refreshCoin("1")

        Mockito.verify(detailsScreen).showNetworkError()
    }

    @Test
    fun testDetailsNotFound() {
        MockCoinsApi.shouldFail = false

        detailsPresenter.refreshCoin("0")

        Mockito.verify(detailsScreen).showNetworkError()
    }

    @After
    fun tearDown() {
        detailsPresenter.detachScreen()
    }
}