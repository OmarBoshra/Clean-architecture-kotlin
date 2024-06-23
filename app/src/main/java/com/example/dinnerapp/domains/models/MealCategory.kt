package com.example.dinnerapp.domains.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealCategory(
    val idMealCategory: String? = "",
    val mealCategoryName: String? = "",
    val mealCategoryUrl: String? = "",
    val mealCategoryDescription: String? = "",
) : Parcelable {
    /**
     * Get category description
     *
     * @return concise description with first line and up-to a punctuation
     */
    fun getCategoryConciseDescription(): String? {
        val lines = mealCategoryDescription?.lines()
        val firstLine = lines?.take(1)
        val rawText = firstLine?.joinToString("\n")
        val endIndex = rawText?.indexOfFirst { it == ',' || it == '.' || it == ';' }
        return if (endIndex != -1) {
            endIndex?.let { rawText.substring(0, it) }
        } else {
            rawText
        }
    }
}
