package hu.gy4ez8.yaccpt.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideCoinsApi(client: OkHttpClient): CoinsApi {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(NetworkConfig.COINLORE_API_ENDPOINT_ADDRESS)
            .build()
        return retrofit.create(CoinsApi::class.java)
    }
}