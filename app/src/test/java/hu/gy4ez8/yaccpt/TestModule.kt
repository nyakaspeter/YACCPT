package hu.gy4ez8.yaccpt

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.gy4ez8.yaccpt.interactor.coins.CoinsInteractor
import hu.gy4ez8.yaccpt.ui.coins.CoinsPresenter
import hu.gy4ez8.yaccpt.ui.details.DetailsPresenter
import hu.gy4ez8.yaccpt.utils.UiExecutor
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class TestModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideCoinsPresenter(executor: Executor, coinsInteractor: CoinsInteractor) = CoinsPresenter(executor, coinsInteractor)

    @Provides
    @Singleton
    fun provideDetailsPresenter(executor: Executor, coinsInteractor: CoinsInteractor) = DetailsPresenter(executor, coinsInteractor)

    @Provides
    @Singleton
    fun provideNetworkExecutor(): Executor = UiExecutor()
}
