package ru.acediat.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.acediat.android.ui.BaseFragment
import ru.acediat.places.databinding.FragmentPlaceBinding
import ru.acediat.places.di.AppModule
import ru.acediat.places.di.DaggerAppComponent
import ru.acediat.places.entities.Place
import javax.inject.Inject

class PlaceFragment : BaseFragment<FragmentPlaceBinding, PlaceViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var picasso: Picasso

    override val viewModel: PlaceViewModel by lazy { createViewModel(viewModelFactory) }

    override fun inject() = DaggerAppComponent.builder()
        .androidModule(AppModule(requireContext()))
        .build()
        .inject(this)

    override fun prepareViews(): Unit = with(binding){
        arguments?.let {
            val placeData = it.getSerializable("key") as Place
            toolbar.title.text = placeData.title
            toolbar.backButton.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.activity_container, PlacesListFragment())
                    .commit()
            }
            place.title.text = placeData.title
            placeDescription.text = placeData.description
            place.description.text = placeData.description
            picasso.load(placeData.photos[0])
                .fit()
                .centerCrop()
                .into(place.backgroundImage)
        }
    }

    override fun getViewModelClass() = PlaceViewModel::class.java

    override fun getBindingInflater(): (
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean,
    ) -> FragmentPlaceBinding = FragmentPlaceBinding::inflate
}