package com.test.vectortest.di.application.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.data.network.INetworkDataSource
import com.test.data.network.NetworkDataSourceImp
import com.test.data.network.retrofit.ApiService
import com.test.data.network.retrofit.BASE_URL
import com.test.vectortest.di.application.Endpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkDataSourceModule {

    @Provides
    @Singleton
    fun provideNetworkDataSource(apiService: ApiService): INetworkDataSource = NetworkDataSourceImp(apiService)

    @Provides
    @Singleton
    fun provideApiService(@Endpoint endPoint: String, gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): ApiService =
            Retrofit.Builder()
                    .baseUrl(endPoint)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .build()
                    .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    @Endpoint
    fun provideEndpoint(): String = BASE_URL
}