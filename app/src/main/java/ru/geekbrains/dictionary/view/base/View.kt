package ru.geekbrains.dictionary.view.base

import ru.geekbrains.dictionary.model.data.DataModel

interface View {

    fun renderData(dataModel: DataModel)

}
