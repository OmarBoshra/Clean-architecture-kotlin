package com.example.dinnerapp.presentation.epoxy.components

import android.annotation.SuppressLint
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.example.dinnerapp.R
import com.example.dinnerapp.databinding.MealCategoriesItemBinding
import com.example.dinnerapp.domains.models.MealCategory
import com.example.dinnerapp.presentation.epoxy.utils.BaseEpoxyModelWithViewHolder
import com.example.dinnerapp.presentation.events.componentevents.MealCategoriesComponentEvents
import com.example.dinnerapp.presentation.utils.ItemState
import com.example.dinnerapp.presentation.utils.NavigationEvents

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.meal_categories_item)
abstract class MealCategoryEpoxyModel :
    BaseEpoxyModelWithViewHolder<MealCategoriesItemBinding>() {
    @EpoxyAttribute
    var itemState: ItemState<MealCategory?>? = null

    override fun MealCategoriesItemBinding.bind() {
        updateBasedOnItemState(
            itemState,
            tvMealCategoryName,
            tvMealCategoryDescription,
            cbMealCategoryCheckbox,
            root,
            ivMealCategories,
        )

        root.setOnClickListener {
            // Check/Uncheck the checkbox & navigate to drink categories.
            cbMealCategoryCheckbox.isChecked = !cbMealCategoryCheckbox.isChecked
            listener?.onEvent(MealCategoriesComponentEvents.OnClickMealCategory(NavigationEvents.ToDrinkCategories))
        }
    }

    private fun updateBasedOnItemState(
        itemState: ItemState<MealCategory?>?,
        tvMealCategoryName: TextView,
        tvMealCategoryDescription: TextView,
        cbMealCategoryCheckbox: CheckBox,
        root: View,
        ivMealCategories: ImageView,
    ) {
        when (itemState) {
            is ItemState.ItemNotSelected -> {
                tvMealCategoryName.text =
                    itemState.data?.mealCategoryName

                tvMealCategoryDescription.text =
                    itemState.data?.getCategoryConciseDescription()

                cbMealCategoryCheckbox.isChecked = false

                updateIcon(root, itemState.data?.mealCategoryUrl, ivMealCategories)
            }

            is ItemState.ItemSelected -> {
                tvMealCategoryName.text =
                    (itemState).data?.mealCategoryName

                tvMealCategoryDescription.text =
                    itemState.data?.getCategoryConciseDescription()

                cbMealCategoryCheckbox.isChecked = true

                updateIcon(root, itemState.data?.mealCategoryUrl, ivMealCategories)
            }

            null -> {}
        }
    }

    private fun updateIcon(
        root: View,
        mealCategoryUrl: String?,
        ivMealCategories: ImageView,
    ) {
        Glide.with(root)
            .load(mealCategoryUrl)
            .centerCrop()
            .into(ivMealCategories)
    }
}
