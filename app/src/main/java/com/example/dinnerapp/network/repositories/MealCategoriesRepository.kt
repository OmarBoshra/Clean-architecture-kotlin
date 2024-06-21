package com.example.dinnerapp.network.repositories

import com.example.dinnerapp.network.api.MealApi
import com.example.dinnerapp.network.domains.MealCategory
import com.example.dinnerapp.network.mappers.MealCategoryMapper
import com.example.dinnerapp.network.utils.SafeApiRequest
import javax.inject.Inject

class MealCategoriesRepository
    @Inject
    constructor(private val service: MealApi) :
    SafeApiRequest() {
        suspend fun getMealCategories(): List<MealCategory?>? {
            val mealCategoriesResponse =
                safeApiRequest { service.getMealCategoriesList() }

            // Mapping to domain .
            val mealCategories =
                mealCategoriesResponse.mealCategoriesList.let {
                    MealCategoryMapper.mapListToDomain(
                        it,
                    )
                }

            return mealCategories
        }
    }
