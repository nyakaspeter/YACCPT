package hu.gy4ez8.yaccpt.ui.coins

import hu.gy4ez8.yaccpt.interactor.coins.CoinsInteractor
import hu.gy4ez8.yaccpt.interactor.coins.event.GetCoinsEvent
import hu.gy4ez8.yaccpt.model.Coin
import hu.gy4ez8.yaccpt.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class CoinsPresenter @Inject constructor(private val executor: Executor, private val coinsInteractor: CoinsInteractor) : Presenter<CoinsScreen>() {
    override fun attachScreen(screen: CoinsScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun refreshCoins() {
        executor.execute {
            //coinsInteractor.getCoins()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetCoinsEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.coins != null) {
                    screen?.showCoins(event.coins as MutableList<Coin>)
                }
            }
        }
    }
}