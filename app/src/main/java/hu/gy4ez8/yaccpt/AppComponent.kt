package hu.gy4ez8.yaccpt

import dagger.Component
import hu.gy4ez8.yaccpt.database.DatabaseModule
import hu.gy4ez8.yaccpt.interactor.InteractorModule
import hu.gy4ez8.yaccpt.network.NetworkModule
import hu.gy4ez8.yaccpt.ui.UIModule
import hu.gy4ez8.yaccpt.ui.coins.CoinsActivity
import hu.gy4ez8.yaccpt.ui.details.DetailsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(coinsActivity: CoinsActivity)
    fun inject(detailsFragment: DetailsFragment)
}