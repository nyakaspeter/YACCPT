package hu.gy4ez8.yaccpt.interactor

import dagger.Module
import dagger.Provides
import hu.gy4ez8.yaccpt.database.CoinDatabase
import hu.gy4ez8.yaccpt.interactor.coins.CoinsInteractor
import hu.gy4ez8.yaccpt.network.CoinsApi
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideCoinsInteractor(coinsApi: CoinsApi, coinDatabase: CoinDatabase) = CoinsInteractor(coinsApi, coinDatabase)
}