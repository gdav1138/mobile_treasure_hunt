package com.example.mobile_treasure_hunt.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update




class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
            AppUiState())

    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun updatePermission(granted: Boolean) {
        _uiState.update {
            it.copy(
                locationPermissionGranted = granted,
                permissionDenied = !granted
            )
        }
    }

//    fun updateRecommendationList(value: String) {
//        _uiState.update {
//            it.copy(
//                    newRecommendation = value,
//                    invalidRecommendation = false
//            )
//        }
//    }
//
//    fun updateRecDetail(value: String) {
//        _uiState.update {
//            it.copy(
//                    newRecDetail = value,
//                    invalidDetail = false
//            )
//        }
//    }
//
//    fun addCategory() {
//        val name = uiState.value.newCategory
//        val newCategory = _model.addCategory(name)
//
//        // If new category entered is not unique, return
//        if (!newCategory) {
//            _uiState.update {
//                it.copy(invalidCategory = true)
//            }
//            return
//        }
//
//        _uiState.update {
//            current -> current.copy(
//                    categories = _model.getCategories(),
//                    newCategory = "",
//                    invalidCategory = false
//            )
//        }
//    }
//
//    fun addRecommendation() {
//        val category = uiState.value.selectedCategory
//        val recommendation = uiState.value.newRecommendation
//
//        _uiState.update {
//            current ->
//                    val existing = current.recommendations[category].orEmpty()
//
//            if (existing.contains(recommendation)) {
//                current.copy(
//                        invalidRecommendation = true
//                )
//            } else {
//                current.copy(
//                        recommendations = current.recommendations +
//                                (category to (existing + recommendation)),
//                        newRecommendation = "",
//                        invalidRecommendation = false
//                )
//            }
//        }
//    }
//
//    fun addDetail() {
//        val detailName = uiState.value.selectedRecommendation
//        val detailText = uiState.value.newRecDetail
//
//        if (detailName.isBlank() || detailText.isBlank())
//            return
//
//                    _uiState.update {
//            current ->
//                    val existing = current.recDetails[detailName].orEmpty()
//
//            if (existing.contains(detailText)) {
//                current.copy(invalidDetail = true)
//            } else {
//                current.copy(
//                        recDetails = current.recDetails + (detailName to (existing + detailText)),
//                        newRecDetail = "",
//                        invalidDetail = false
//                )
//            }
//        }
//    }
//
//    fun deleteCategory(name: String) {
//        _model.deleteCategory(name)
//
//        _uiState.update {
//            current -> current.copy(
//                    // Updating with new list of categories minus the category to be deleted
//                    categories = _model.getCategories(),
//                    selectedCategory = if (current.selectedCategory == name) {
//                ""
//            } else current.selectedCategory
//        )
//        }
//    }
//
//    fun deleteRecommendation(name: String) {
//        val category = uiState.value.selectedCategory
//
//        _uiState.update {
//            current ->
//                    val existing = current.recommendations[category].orEmpty()
//            current.copy(
//                    // Updating with new list of recommendations minus the recommendation to be deleted
//                    recommendations = current.recommendations +
//                            (category to existing.filterNot {it == name})
//
//            )
//        }
//    }
//
//    fun deleteDetail(detailText: String) {
//        val detailName = uiState.value.selectedRecommendation
//
//        _uiState.update { current ->
//                val existing = current.recDetails[detailName].orEmpty()
//
//            current.copy(
//                    recDetails = current.recDetails + (detailName to existing.filterNot {
//                it == detailText
//            })
//            )
//        }
//    }
//
//    fun selectCategory(name: String) {
//        _uiState.update {
//            it.copy(selectedCategory = name)
//        }
//    }
//
//    fun selectRecommendation(name: String) {
//        _uiState.update {
//            it.copy(selectedRecommendation = name)
//        }
//    }
}