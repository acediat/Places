package ru.acediat.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding, VM: ViewModel> : Fragment() {

    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    protected abstract val viewModel: VM

    protected open fun inject() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()
        _binding = inflateBinding(container)
        prepareViewModel()
        prepareViews()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun prepareViews()

    protected open fun prepareViewModel() {}

    protected abstract fun getViewModelClass(): Class<VM>

    protected abstract fun getBindingInflater(): (
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) -> B

    protected fun inflateBinding(container: ViewGroup?) = getBindingInflater()
        .invoke(layoutInflater, container, false)

    protected fun createViewModel(viewModelFactory: ViewModelProvider.Factory): VM = ViewModelProvider(
        this, viewModelFactory
    )[getViewModelClass()]

}