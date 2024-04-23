package com.snick55.presentation

import androidx.annotation.RestrictTo
import com.snick55.core.Feature
import com.snick55.domain.GetAllOffersUseCase
import com.snick55.domain.GetAllTicketsUseCase
import com.snick55.domain.TownsInteractor
import com.snick55.domain.repositories.TicketsRepository
import com.snick55.presentation.allTickets.AllTicketsFragment
import com.snick55.presentation.search.SearchWithCountryFragment
import com.snick55.presentation.tickets.AviaTicketsFragment
import dagger.Component
import kotlin.properties.Delegates.notNull

@[Feature Component(dependencies = [PresentationDeps::class])]
internal interface PresentationComponent {

    fun inject(fragment: AviaTicketsFragment)
    fun inject(fragmentArgs: SearchWithCountryFragment)
    fun inject(fragment: AllTicketsFragment)

    @Component.Builder
    interface Builder {

        fun deps(presentationDeps: PresentationDeps): Builder
        fun build(): PresentationComponent
    }

}

interface PresentationDeps {

    val useCase: GetAllOffersUseCase

    val repository: TicketsRepository

    val getAllTicketsUseCase:GetAllTicketsUseCase

    val townsInteractor: TownsInteractor


}

interface PresentationDepsProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: PresentationDeps

    companion object : PresentationDepsProvider by PresentationDepsStore
}

object PresentationDepsStore : PresentationDepsProvider {

    override var deps: PresentationDeps by notNull()
}

