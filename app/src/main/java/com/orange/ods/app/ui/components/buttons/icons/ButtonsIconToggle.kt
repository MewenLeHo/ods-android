/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons.icons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.buttons.InvertedBackgroundColumn
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CodeParameter
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.IconPainterValue
import com.orange.ods.app.ui.utilities.composable.PredefinedParameter
import com.orange.ods.app.ui.utilities.composable.SimpleParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsIconToggleButton

@Composable
fun ButtonsIconToggle(customizationState: ButtonIconCustomizationState) {
    val buttonCheckedState = rememberSaveable { mutableStateOf(false) }

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OdsIconToggleButton(
                    checked = buttonCheckedState.value,
                    uncheckedPainter = painterResource(id = R.drawable.ic_heart_outlined),
                    checkedPainter = painterResource(id = R.drawable.ic_heart),
                    iconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_icon_desc),
                    onCheckedChange = { checked -> buttonCheckedState.value = checked },
                    enabled = isEnabled
                )
            }

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                OdsIconToggleButton(
                    checked = buttonCheckedState.value,
                    uncheckedPainter = painterResource(id = R.drawable.ic_heart_outlined),
                    checkedPainter = painterResource(id = R.drawable.ic_heart),
                    iconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_icon_desc),
                    onCheckedChange = { checked -> buttonCheckedState.value = checked },
                    enabled = isEnabled,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            ) {
                FunctionCallCode(name = OdsComponent.OdsIconToggleButton.name, exhaustiveParameters = false, parameters = mutableListOf<CodeParameter>(
                    PredefinedParameter.Painter,
                    SimpleParameter("painterChecked", IconPainterValue),
                    PredefinedParameter.Checked(buttonCheckedState.value)
                ).apply {
                    if (!isEnabled) {
                        add(PredefinedParameter.Enabled(false))
                    }
                })
            }
        }
    }
}
