package ru.geekbrains.repository

import ru.geekbrains.model.data.DataModel

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(dataModel: DataModel)
}
