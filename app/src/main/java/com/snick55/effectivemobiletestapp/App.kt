package com.snick55.effectivemobiletestapp

import android.app.Application
import android.content.Context
import com.snick55.effectivemobiletestapp.di.AppComponent
import com.snick55.effectivemobiletestapp.di.DaggerAppComponent
import com.snick55.presentation.PresentationDepsStore

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        PresentationDepsStore.deps = appComponent


    }
}