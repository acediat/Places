package ru.acediat.android.ui

import androidx.viewbinding.ViewBinding
import ru.acediat.android.Effect
import ru.acediat.android.State
import ru.acediat.android.StateRxViewModel

abstract class StateFragment<B: ViewBinding, VM: StateRxViewModel<S, E>, S: State, E: Effect> : BaseFragment<B, VM>() {

    override fun prepareViewModel() = with(viewModel) {
        state.observe(this@StateFragment, ::onStateChanged)
        effect.observe(this@StateFragment, ::onEffect)
    }

    abstract fun onStateChanged(state: S)

    abstract fun onEffect(effect: E)
}