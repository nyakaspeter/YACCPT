package hu.gy4ez8.yaccpt

import dagger.Component
import hu.gy4ez8.yaccpt.interactor.InteractorModule
import hu.gy4ez8.yaccpt.mock.MockNetworkModule
import hu.gy4ez8.yaccpt.test.CoinsTest
import hu.gy4ez8.yaccpt.test.DetailsTest
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule::class, TestModule::class, InteractorModule::class])
interface TestComponent : AppComponent {
    fun inject(coinsTest: CoinsTest)
    fun inject(detailsTest: DetailsTest)
}
