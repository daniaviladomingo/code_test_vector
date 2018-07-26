package com.test.vectortest.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<View : BaseView>(protected val view: View) : ScopePresenter {

    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onStop() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
