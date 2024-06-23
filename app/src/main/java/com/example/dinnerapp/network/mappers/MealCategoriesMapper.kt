package com.example.dinnerapp.network.mappers

import com.example.dinnerapp.domains.models.MealCategory
import com.example.dinnerapp.data.models.MealCategoryResponse

object MealCategoryMapper {
    fun mapToDomain(response: MealCategoryResponse): MealCategory {
        return MealCategory(
            idMealCategory = response.idMealCategory,
            mealCategoryName = response.mealCategoryName,
            mealCategoryDescription = response.mealCategoryDescription,
            mealCategoryUrl = response.mealCategoryUrl,
        )
    }

    fun mapListToDomain(responseList: List<MealCategoryResponse>): List<MealCategory> {
        return responseList.map { mapToDomain(it) }
    }
}
