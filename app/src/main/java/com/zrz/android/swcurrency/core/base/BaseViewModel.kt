package com.zrz.android.swcurrency.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zrz.android.swcurrency.util.extension.threadingSubscribe
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    val throwableLD: MutableLiveData<Throwable> = MutableLiveData()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun reportError(throwable: Throwable) {
        throwableLD.value = throwable
    }

    fun <T> doSingle(
        single: Single<T>,
        errorBlock: (Throwable) -> Unit = this::reportError,
        successBlock: (T) -> Unit
    ) {
        compositeDisposable.add(
            single
                .threadingSubscribe()
                .subscribe(successBlock::invoke, errorBlock::invoke)
        )
    }
}