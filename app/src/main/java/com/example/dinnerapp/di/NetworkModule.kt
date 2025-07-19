package com.example.dinnerapp.di

import com.example.dinnerapp.network.api.MealApi
import com.example.dinnerapp.network.repositories.MealCategoriesRepository
import com.example.dinnerapp.network.usecases.GetMealCategoriesUsecase
import com.example.dinnerapp.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideMealApi(): MealApi {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constants.MealConstants.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MealApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMealCategoriesRepository(mealApi: MealApi): MealCategoriesRepository {
        return MealCategoriesRepository(mealApi)
    }

    @Provides
    @Singleton
    fun provideMealCategoriesUsecase(repository: MealCategoriesRepository): GetMealCategoriesUsecase {
        return GetMealCategoriesUsecase(repository)
    }
}
