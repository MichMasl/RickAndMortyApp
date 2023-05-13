package ru.mihmas.rickandmortyapp.core

import android.app.Application
import ru.mihmas.rickandmortyapp.core.di.ApplicationComponent
import ru.mihmas.rickandmortyapp.core.di.DaggerApplicationComponent

class App : Application() {
    val component: ApplicationComponent = DaggerApplicationComponent
        .factory()
        .create(context = this)
}