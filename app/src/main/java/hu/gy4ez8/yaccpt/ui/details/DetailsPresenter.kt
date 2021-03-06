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
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    override fun detachScreen() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.detachScreen()
    }

    fun refreshCoin(id: String) {
        executor.execute {
            coinsInteractor.getCoinDetails(id)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetCoinDetailsEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError()
            }
        }

        if (screen != null && event.coin != null) {
            screen?.showCoin(event.coin as Coin)
        }
    }
}