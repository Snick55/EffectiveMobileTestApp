package com.snick55.effectivemobiletestapp.di

import android.content.Context
import android.content.SharedPreferences
import com.snick55.data.offers.OffersDataSource
import com.snick55.data.offers.OffersRepositoryImpl
import com.snick55.data.tickets.TicketsDataSource
import com.snick55.data.tickets.TicketsRepositoryImpl
import com.snick55.data.towns.TownsPreferenceDataSource
import com.snick55.data.towns.TownsRepositoryImpl
import com.snick55.domain.GetAllOffersUseCase
import com.snick55.domain.GetAllTicketsUseCase
import com.snick55.domain.GetTownUseCase
import com.snick55.domain.repositories.OffersRepository
import com.snick55.domain.SaveTownUseCase
import com.snick55.domain.repositories.TicketsRepository
import com.snick55.domain.TownsInteractor
import com.snick55.domain.repositories.TownsRepository
import com.snick55.presentation.allTickets.AllTicketsViewModelFactory
import com.snick55.presentation.tickets.AviaTicketsViewModelFactory
import com.snick55.presentation.search.SearchWithCountryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideOffersRepository(dataSource: OffersDataSource): OffersRepository {
        return OffersRepositoryImpl(dataSource)
    }

    @Provides
    fun provideOffersDataSource(context: Context): OffersDataSource {
        return OffersDataSource.OffersDataSourceImpl(context)
    }

    @Provides
    fun provideAviaTicketsViewModelFactory(
        useCase: GetAllOffersUseCase,
        interactor: TownsInteractor
    ): AviaTicketsViewModelFactory{
        return  AviaTicketsViewModelFactory(useCase,interactor)
    }

    @Provides
    fun provideTownsInteractor(
        saveUseCase: SaveTownUseCase,
        getUseCase: GetTownUseCase
    ): TownsInteractor{
        return TownsInteractor.TownsInteractorImpl(saveUseCase,getUseCase)
    }

    @Provides
    fun provideTownsRepository(
        preferenceDataSource: TownsPreferenceDataSource
    ): TownsRepository {
        return  TownsRepositoryImpl(preferenceDataSource)
    }

    @Provides
    fun provideTownsPreferenceDataSource(
        sharedPreferences: SharedPreferences
    ): TownsPreferenceDataSource {
        return TownsPreferenceDataSource.TownsPreferenceDataSourceImpl(sharedPreferences)
    }

    @Provides
    fun provideSharedPreferences(context: Context):SharedPreferences{
        return context.getSharedPreferences("towns",Context.MODE_PRIVATE)
    }

    @Provides
    fun provideSearchWithCountryViewModelFactory(
       repository: TicketsRepository
    ): SearchWithCountryViewModelFactory {
        return  SearchWithCountryViewModelFactory(repository)
    }

    @Provides
    fun provideTicketsRepository(
        dataSource: TicketsDataSource
    ): TicketsRepository {
        return TicketsRepositoryImpl(dataSource)
    }

    @Provides
    fun provideTicketsDataSource(
        context: Context
    ): TicketsDataSource {
        return TicketsDataSource.TicketsDataSourceImpl(context)
    }

    @Provides
    fun provideGetAllTicketsUseCase(repository: TicketsRepository):GetAllTicketsUseCase{
        return GetAllTicketsUseCase(repository)
    }

    @Provides
    fun provideAllTicketsViewModelFactory(
        useCase: GetAllTicketsUseCase
    ): AllTicketsViewModelFactory{
        return AllTicketsViewModelFactory(useCase)
    }

}