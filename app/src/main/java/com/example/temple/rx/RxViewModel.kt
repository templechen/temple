package com.example.temple.rx

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class RxViewModel(application: Application) : AndroidViewModel(application) {

    private val mCompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun register(subscription: Disposable) {
        mCompositeDisposable.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }
}