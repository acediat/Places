package ru.acediat.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StateRxViewModel<S: State, E: Effect> : BaseRxViewModel() {

    protected val _state = MutableLiveData<S>()
    val state: LiveData<S> = _state

    protected val _effect = SingleLiveEvent<E>()
    val effect: LiveData<E> = _effect
}