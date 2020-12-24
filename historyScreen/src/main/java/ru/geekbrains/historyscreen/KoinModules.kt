package ru.geekbrains.historyscreen

import ru.geekbrains.historyscreen.view.history.HistoryInteractor
import ru.geekbrains.historyscreen.view.history.HistoryViewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectDependencies() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(listOf(historyScreen))
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}
