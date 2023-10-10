package ru.acediat.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import ru.acediat.android.ui.StateFragment
import ru.acediat.places.databinding.FragmentPlacesBinding
import ru.acediat.places.databinding.LayoutLoadingBinding
import ru.acediat.places.databinding.LayoutRecyclerViewBinding
import ru.acediat.places.di.AppModule
import ru.acediat.places.di.DaggerAppComponent
import ru.acediat.places.entities.Place
import javax.inject.Inject

class PlacesListFragment : StateFragment<FragmentPlacesBinding, PlacesListViewModel, PlacesListState, PlacesListEffect>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var placesAdapter: PlacesAdapter

    override val viewModel: PlacesListViewModel by lazy { createViewModel(viewModelFactory) }

    override fun inject() = DaggerAppComponent.builder()
        .androidModule(AppModule(requireContext()))
        .build()
        .inject(this)

    override fun prepareViews(): Unit = with(binding) {
        toolbar.title.text = "Обзорные"
        toolbar.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        viewModel.getAllPlaces()
    }

    override fun onStateChanged(state: PlacesListState) = when (state) {
        is PlacesListState.Loading -> showLoadingView()
        is PlacesListState.PlacesReceived -> showPlacesView(state.places)
    }

    override fun onEffect(effect: PlacesListEffect) {}

    override fun getViewModelClass() = PlacesListViewModel::class.java

    override fun getBindingInflater(): (
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean,
    ) -> FragmentPlacesBinding = FragmentPlacesBinding::inflate

    private fun showLoadingView() = with(binding.container) {
        removeAllViews()
        addView(LayoutLoadingBinding.inflate(layoutInflater, this, false).root)
    }

    private fun showPlacesView(places: List<Place>) = with(binding.container) {
        removeAllViews()
        addView(
            LayoutRecyclerViewBinding.inflate(
                layoutInflater,
                this,
                false
            ).recyclerView.apply {
                adapter = placesAdapter.apply {
                    setPlaces(places)
                    setOnClickListener {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.activity_container, PlaceFragment().apply {
                                arguments = bundleOf("key" to it)
                            })
                            .commit()
                    }
                }
            }
        )
    }
}