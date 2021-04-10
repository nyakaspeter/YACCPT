package hu.gy4ez8.yaccpt.ui.details

import hu.gy4ez8.yaccpt.interactor.coins.CoinsInteractor
import hu.gy4ez8.yaccpt.interactor.coins.event.GetCoinDetailsEvent
import hu.gy4ez8.yaccpt.model.Coin
import hu.gy4ez8.yaccpt.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val executor: Executor, private val coinsInteractor: CoinsInteractor) : Presenter<DetailsScreen>() {
    override fun attachScreen(screen: DetailsScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun refreshCoin() {
        executor.execute {
            //coinsInteractor.getCoin()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetCoinDetailsEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.coin != null) {
                    screen?.showCoin(event.coin as Coin)
                }
            }
        }
    }
}