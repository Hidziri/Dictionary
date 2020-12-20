package ru.geekbrains.historyscreen.view.history

import ru.geekbrains.core.viewmodel.Interactor
import ru.geekbrains.model.data.DataModel
import ru.geekbrains.model.data.SearchResult
import ru.geekbrains.repository.Repository
import ru.geekbrains.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResult>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResult>>
) : Interactor<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        return DataModel.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
