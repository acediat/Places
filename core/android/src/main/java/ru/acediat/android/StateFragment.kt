package ru.acediat.android

import androidx.viewbinding.ViewBinding

abstract class StateFragment<B: ViewBinding, VM: StateRxViewModel<S, E>, S: State, E: Effect>: BaseFragment<B, VM>() {


}