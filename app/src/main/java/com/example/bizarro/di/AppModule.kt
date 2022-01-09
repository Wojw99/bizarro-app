package com.example.bizarro.di

import com.example.bizarro.api.BizarroApi
import com.example.bizarro.api.deserializers.CustomDateDeserializer
import com.example.bizarro.api.deserializers.CustomDateSerializer
import com.example.bizarro.api.deserializers.MarkInfoDeserializer
import com.example.bizarro.api.models.MarkInfo
import com.example.bizarro.managers.TokenManager
import com.example.bizarro.repositories.OpinionsRepository
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.FilterState
import com.example.bizarro.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
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
    fun provideTokenManager(): TokenManager {
        return TokenManager()
    }

    @Singleton
    @Provides
    fun provideRecordRepository(
        api: BizarroApi,
        userRepository: UserRepository,
        tokenManager: TokenManager
    ): RecordRepository {
        return RecordRepository(api, userRepository, tokenManager)
    }

    @Singleton
    @Provides
    fun provideUserRepository(api: BizarroApi, tokenManager: TokenManager): UserRepository {
        return UserRepository(api, tokenManager)
    }

    @Singleton
    @Provides
    fun provideOpinionsRepository(api: BizarroApi, tokenManager: TokenManager): OpinionsRepository {
        return OpinionsRepository(api, tokenManager)
    }

    @Singleton
    @Provides
    fun provideBizarroApi(): BizarroApi {
        val customGson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, CustomDateDeserializer())
            .registerTypeAdapter(LocalDate::class.java, CustomDateSerializer())
            .registerTypeAdapter(MarkInfo::class.java, MarkInfoDeserializer())
            .create()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(customGson))
            .baseUrl(Constants.BASE_URL)
            .client(httpClient.build())
            .build()
            .create(BizarroApi::class.java)
    }
}