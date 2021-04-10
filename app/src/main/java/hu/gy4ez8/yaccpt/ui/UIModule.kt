package hu.gy4ez8.yaccpt.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.gy4ez8.yaccpt.interactor.coins.CoinsInteractor
import hu.gy4ez8.yaccpt.ui.coins.CoinsPresenter
import hu.gy4ez8.yaccpt.ui.details.DetailsPresenter
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun coinsPresenter(executor: Executor, coinsInteractor: CoinsInteractor) = CoinsPresenter(executor, coinsInteractor)

    @Provides
    @Singleton
    fun detailsPresenter(executor: Executor, coinsInteractor: CoinsInteractor) = DetailsPresenter(executor, coinsInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}