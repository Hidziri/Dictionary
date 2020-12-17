package ru.geekbrains.dictionary.di

import androidx.room.Room
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.datasource.RetrofitImplementation
import ru.geekbrains.dictionary.model.datasource.RoomDataBaseImplementation
import ru.geekbrains.dictionary.model.repository.Repository
import ru.geekbrains.dictionary.model.repository.RepositoryImplementation
import ru.geekbrains.dictionary.view.main.MainInteractor
import ru.geekbrains.dictionary.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.dictionary.model.repository.RepositoryImplementationLocal
import ru.geekbrains.dictionary.model.repository.RepositoryLocal
import ru.geekbrains.dictionary.room.HistoryDataBase
import ru.geekbrains.dictionary.view.history.HistoryInteractor
import ru.geekbrains.dictionary.view.history.HistoryViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
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
