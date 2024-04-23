package com.snick55.presentation

import androidx.lifecycle.ViewModel

internal class PresentationComponentViewModel : ViewModel() {

    val newDetailsComponent =
        DaggerPresentationComponent.builder().deps(PresentationDepsProvider.deps).build()
}