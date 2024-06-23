package com.example.dinnerapp.domains.usecases

import com.example.dinnerapp.domains.models.MealCategory
import com.example.dinnerapp.data.repositories.MealCategoriesRepository
import com.example.dinnerapp.network.utils.Result
import javax.inject.Inject

class GetMealCategoriesUsecase
    @Inject
    constructor(private val repository: MealCategoriesRepository) {
        suspend operator fun invoke(): Result<List<MealCategory?>?> {
            return try {
                val mealCategories = repository.getMealCategories()
                Result.Success(mealCategories)
            } catch (e: Exception) {
                Result.Error(e.message)
            }
        }
    }
