package com.test.vectortest.di.application

import java.lang.annotation.Retention
import javax.inject.Qualifier
import java.lang.annotation.RetentionPolicy.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Endpoint