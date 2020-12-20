package ru.geekbrains.dictionary.di

import androidx.room.Room
import ru.geekbrains.historyscreen.view.history.HistoryInteractor
import ru.geekbrains.historyscreen.view.history.HistoryViewModel
import ru.geekbrains.model.data.SearchResult
import ru.geekbrains.repository.room.HistoryDataBase
import ru.geekbrains.repository.*
import ru.geekbrains.dictionary.view.main.MainInteractor
import ru.geekbrains.dictionary.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<SearchResult>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<SearchResult>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}