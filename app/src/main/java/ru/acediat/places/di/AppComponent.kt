package ru.acediat.places.di

import dagger.Component
import ru.acediat.places.PlaceFragment
import ru.acediat.places.PlacesListFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppModule.Bindings::class,
        NetworkModule::class,
    ]
)
interface AppComponent {

    fun inject(impl: PlacesListFragment)
    fun inject(impl: PlaceFragment)

    @Component.Builder
    interface Builder {
        fun androidModule(module: AppModule): Builder
        fun build(): AppComponent
    }
}