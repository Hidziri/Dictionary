package ru.geekbrains.dictionary.model.repository

import ru.geekbrains.dictionary.model.datasource.DataSource
import ru.geekbrains.dictionary.model.data.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
