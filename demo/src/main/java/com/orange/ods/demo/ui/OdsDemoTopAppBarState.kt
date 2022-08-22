/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.demo.R

private const val DefaultTopAppBarActionCount = 1

@Composable
fun rememberOdsDemoTopAppBarState(
    titleRes: MutableState<Int> = rememberSaveable { mutableStateOf(R.string.navigation_item_guidelines) },
    actionCount: MutableState<Int> = rememberSaveable { mutableStateOf(DefaultTopAppBarActionCount) },
    navigationIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    overflowMenuEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(titleRes, actionCount, navigationIconEnabled, overflowMenuEnabled) {
        OdsDemoTopAppBarState(titleRes, actionCount, navigationIconEnabled, overflowMenuEnabled)
    }

class OdsDemoTopAppBarState(
    val titleRes: MutableState<Int>,
    val actionCount: MutableState<Int>,
    private val navigationIconEnabled: MutableState<Boolean>,
    private val overflowMenuEnabled: MutableState<Boolean>
) {

    companion object {
        val defaultConfiguration = TopAppBarConfiguration(
            isNavigationIconEnabled = true,
            actionCount = DefaultTopAppBarActionCount,
            isOverflowMenuEnabled = false
        )
    }

    // ----------------------------------------------------------
    // TopAppBar state source of truth
    // ----------------------------------------------------------

    val isNavigationIconEnabled: Boolean
        get() = navigationIconEnabled.value

    val isOverflowMenuEnabled: Boolean
        get() = overflowMenuEnabled.value

    fun updateTopAppBar(topAppBarConfiguration: TopAppBarConfiguration) {
        navigationIconEnabled.value = topAppBarConfiguration.isNavigationIconEnabled
        actionCount.value = topAppBarConfiguration.actionCount
        overflowMenuEnabled.value = topAppBarConfiguration.isOverflowMenuEnabled
    }

    fun updateTopAppBarTitle(titleRes: Int) {
        this.titleRes.value = titleRes
    }

}

data class TopAppBarConfiguration constructor(
    val isNavigationIconEnabled: Boolean,
    val actionCount: Int,
    val isOverflowMenuEnabled: Boolean
)