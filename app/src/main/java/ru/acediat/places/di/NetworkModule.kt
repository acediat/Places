package ru.acediat.places.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.acediat.network.buildApi
import ru.acediat.places.Api
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideGson() : Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun provideHttpClient() : OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .readTimeout(35, TimeUnit.SECONDS)
        .writeTimeout(35, TimeUnit.SECONDS)
        .connectTimeout(35, TimeUnit.SECONDS)
        .build()


    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        client : OkHttpClient,
        gson : Gson
    ) : Retrofit.Builder = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

    @Provides
    fun provideApi(builder: Retrofit.Builder) = builder.buildApi<Api>()
}