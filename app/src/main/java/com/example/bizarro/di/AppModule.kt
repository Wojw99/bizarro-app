package com.example.bizarro.di

import com.example.bizarro.data.remote.BizarroApi
import com.example.bizarro.data.remote.deserializers.CustomDateDeserializer
import com.example.bizarro.data.remote.deserializers.CustomDateSerializer
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.FilterState
import com.example.bizarro.util.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.util.logging.Filter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // Dependencies here live as long as the whole application.
    // All dependencies are singletons.

    @Singleton
    @Provides
    fun provideFilterState(): FilterState {
        return FilterState()
    }

    @Singleton
    @Provides
    fun provideAppState(): AppState {
        return AppState()
    }

    @Singleton
    @Provides
    fun provideRecordRepository(api: BizarroApi): RecordRepository {
        return RecordRepository(api)
    }

    @Singleton
    @Provides
    fun provideUserRepository(api: BizarroApi): UserRepository {
        return UserRepository(api)
    }

    @Singleton
    @Provides
    fun provideBizarroApi(): BizarroApi {
        val customGson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, CustomDateDeserializer())
            .registerTypeAdapter(LocalDate::class.java, CustomDateSerializer())
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(customGson))
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(BizarroApi::class.java)
    }
}