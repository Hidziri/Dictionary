package ru.geekbrains.dictionary.view.main

import ru.geekbrains.core.viewmodel.Interactor
import ru.geekbrains.model.data.DataModel
import ru.geekbrains.model.data.SearchResult
import ru.geekbrains.repository.Repository
import ru.geekbrains.repository.RepositoryLocal

class MainInteractor(
    private val repositoryRemote: Repository<List<SearchResult>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResult>>
) : Interactor<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        val dataModel: DataModel
        if (fromRemoteSource) {
            dataModel = DataModel.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(dataModel)
        } else {
            dataModel = DataModel.Success(repositoryLocal.getData(word))
        }
        return dataModel
    }
}
