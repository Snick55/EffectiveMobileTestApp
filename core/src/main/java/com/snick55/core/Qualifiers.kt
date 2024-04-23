package com.snick55.core

import javax.inject.Qualifier
import javax.inject.Scope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Feature