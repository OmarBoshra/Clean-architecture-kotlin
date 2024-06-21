package com.example.dinnerapp.network.usecases

import com.example.dinnerapp.network.domains.MealCategory
import com.example.dinnerapp.network.repositories.MealCategoriesRepository
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
