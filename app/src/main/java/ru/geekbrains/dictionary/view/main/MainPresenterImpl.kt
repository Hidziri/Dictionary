package ru.geekbrains.dictionary.view.main

import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.model.datasource.DataSourceLocal
import ru.geekbrains.dictionary.model.datasource.DataSourceRemote
import ru.geekbrains.dictionary.model.repository.RepositoryImplementation
import ru.geekbrains.dictionary.presenter.Presenter
import ru.geekbrains.dictionary.rx.SchedulerProvider
import ru.geekbrains.dictionary.view.base.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class MainPresenterImpl<T : DataModel, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribeWith(getObserver())
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { currentView?.renderData(DataModel.Loading(null)) }

    private fun getObserver(): DisposableObserver<DataModel> {
        return object : DisposableObserver<DataModel>() {

            override fun onNext(data: DataModel) {
                currentView?.renderData(data)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(DataModel.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}
