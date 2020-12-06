package ru.geekbrains.dictionary.model.repository

import ru.geekbrains.dictionary.model.data.SearchResult
import ru.geekbrains.dictionary.model.datasource.DataSource
import io.reactivex.Observable
import ru.geekbrains.dictionary.model.repository.Repository

class RepositoryImplementation(private val dataSource: DataSource<List<SearchResult>>) :
    Repository<List<SearchResult>> {

    override fun getData(word: String): Observable<List<SearchResult>> {
        return dataSource.getData(word)
    }
}
