package hu.gy4ez8.yaccpt.mock


import dagger.Module
import dagger.Provides
import hu.gy4ez8.yaccpt.network.CoinsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class MockNetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideCoinsApi(): CoinsApi = MockCoinsApi()
}
