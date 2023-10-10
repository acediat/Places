package ru.acediat.places

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.acediat.android.Effect
import ru.acediat.android.State
import ru.acediat.android.StateRxViewModel
import ru.acediat.places.entities.Place
import javax.inject.Inject

sealed class PlacesListState : State {
    object Loading : PlacesListState()
    data class PlacesReceived(
        val places: List<Place>,
    ) : PlacesListState()
}

sealed class PlacesListEffect : Effect

class PlacesListViewModel @Inject constructor(
    private val repository: ApiRepository,
) : StateRxViewModel<PlacesListState, PlacesListEffect>() {

    fun getAllPlaces() = compositeDisposable.add(repository.getAllPlaces()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { _state.postValue(PlacesListState.Loading) }
        .subscribe({
            _state.postValue(PlacesListState.PlacesReceived(it))
        }, {
            Log.e("viewModel", "", it)
        }))

}