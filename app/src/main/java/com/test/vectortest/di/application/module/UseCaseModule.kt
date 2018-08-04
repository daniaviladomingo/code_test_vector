package com.test.vectortest.di.application.module

import com.test.domain.interactors.GetCachedUserSingleUserCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.domain.interactors.type.SingleUseCaseWithParameter
import com.test.domain.model.User
import com.test.domain.repository.IRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetUsersSingleUseCase(repository: IRepository): GetUsersSingleUseCase = GetUsersSingleUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCachedUsersSingleUseCase(repository: IRepository): GetCachedUserSingleUserCase = GetCachedUserSingleUserCase(repository)
}