package com.test.vectortest.di.application.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.data.network.INetworkDataSource
import com.test.data.network.NetworkDataSourceImp
import com.test.data.network.model.UserApi
import com.test.data.network.retrofit.ApiService
import com.test.data.network.retrofit.BASE_URL
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import com.test.vectortest.di.application.Endpoint
import com.test.vectortest.di.application.Password
import com.test.vectortest.di.application.StringCredentials
import com.test.vectortest.di.application.Username
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkDataSourceModule {
    @Provides
    @Singleton
    fun provideNetworkDataSource(apiService: ApiService, networkMapper: Mapper<UserApi, User>): INetworkDataSource = NetworkDataSourceImp(apiService, networkMapper)

    @Provides
    @Singleton
    fun provideApiService(@Endpoint endPoint: String, httpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): ApiService =
            Retrofit.Builder()
                    .baseUrl(endPoint)
                    .client(httpClient)
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

    @Provides
    @Singleton
    fun provideHttpClient(@StringCredentials credentials: String): OkHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
        chain.run {
            request()
            val builder = request().newBuilder().header("Authorization", credentials)
            proceed(builder.build())
        }
    }.build()

    @Provides
    @Singleton
    @StringCredentials
    fun provideCredentials(@Username username: String, @Password password: String): String = Credentials.basic(username, password)

    @Provides
    @Singleton
    @Username
    fun provideUserName(): String = ""

    @Provides
    @Singleton
    @Password
    fun providePassword(): String = ""
}