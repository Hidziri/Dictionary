package ru.geekbrains.dictionary.view.main

import com.anikin.aleksandr.simplevocabulary.viewmodel.Interactor
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.data.SearchResult
import ru.geekbrains.dictionary.model.repository.Repository
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<SearchResult>>,
    private val localRepository: Repository<List<SearchResult>>
) : Interactor<DataModel> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<DataModel> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { DataModel.Success(it) }
        } else {
            localRepository.getData(word).map { DataModel.Success(it) }
        }
    }
}
