package ru.acediat.places.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import ru.acediat.android.ViewModelFactory
import ru.acediat.core_android.model.ViewModelKey
import ru.acediat.places.PlacesListViewModel
import javax.inject.Singleton

@Module
class AppModule(
    private val context: Context,
) {

    @Provides
    @Singleton
    fun providePicasso(client: OkHttpClient): Picasso = Picasso.Builder(context)
        .loggingEnabled(true)
        .downloader(OkHttp3Downloader(client))
        .build()

    @Module
    interface Bindings {

        @Binds
        fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
        @Binds
        @IntoMap
        @ViewModelKey(PlacesListViewModel::class)
        fun bindEventDetailViewModel(viewModel: PlacesListViewModel): ViewModel
    }
}