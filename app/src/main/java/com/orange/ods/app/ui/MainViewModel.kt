/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.ods.app.domain.datastore.DataStoreService
import com.orange.ods.app.domain.recipes.Category
import com.orange.ods.app.domain.recipes.Recipe
import com.orange.ods.app.domain.recipes.RecipesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreService: DataStoreService, private val recipesService: RecipesService) : ViewModel() {

    companion object {
        private const val UserThemeNameKey = "userThemeName"
    }

    var recipes = emptyList<Recipe>()
        private set

    var categories = emptyList<Category>()
        private set

    init {
        viewModelScope.launch {
            recipes = recipesService.getRecipes().firstOrNull().orEmpty()
            categories = recipes.map { it.category }.distinct().sortedBy { it.id }
        }
    }

    fun storeUserThemeName(themeName: String) = runBlocking {
        dataStoreService.putString(UserThemeNameKey, themeName)
    }

    fun getUserThemeName(): String? = runBlocking { dataStoreService.getString(UserThemeNameKey) }
}