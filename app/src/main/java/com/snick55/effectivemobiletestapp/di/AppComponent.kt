package com.snick55.effectivemobiletestapp.di

import android.content.Context
import com.snick55.domain.GetAllOffersUseCase
import com.snick55.domain.GetAllTicketsUseCase
import com.snick55.domain.repositories.TicketsRepository
import com.snick55.domain.TownsInteractor
import com.snick55.presentation.PresentationDeps
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class,DispatcherModule::class])]
interface AppComponent : PresentationDeps {

    override val useCase: GetAllOffersUseCase

    override val townsInteractor: TownsInteractor

    override val repository: TicketsRepository

    override val getAllTicketsUseCase: GetAllTicketsUseCase

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

@Scope
annotation class AppScope