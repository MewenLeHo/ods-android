/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.card.OdsSmallCard

@Composable
fun CardSmall(customizationState: CardCustomizationState) {
    val context = LocalContext.current
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.random() }

    with(customizationState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.spacing_m))
                .verticalScroll(state = rememberScrollState()),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
            ) {
                val cardText = stringResource(id = R.string.component_card_element_card)

                OdsSmallCard(
                    modifier = Modifier.weight(0.5f),
                    image = rememberAsyncImagePainter(
                        model = recipe.imageUrl,
                        placeholder = painterResource(id = DrawableManager.getPlaceholderResId()),
                        error = painterResource(id = DrawableManager.getPlaceholderResId(error = true))
                    ),
                    title = recipe.title,
                    subtitle = if (subtitleChecked.value) recipe.subtitle else null,
                    onCardClick = if (isClickable) {
                        { clickOnElement(context, cardText) }
                    } else null
                )
                Box(modifier = Modifier.weight(0.5f))
            }

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            CodeImplementationColumn {
                FunctionCallCode(
                    name = OdsComposable.OdsSmallCard.name,
                    exhaustiveParameters = false,
                    parameters = {
                        title(recipe.title)
                        image()
                        if (hasSubtitle) subtitle(recipe.subtitle)
                        if (isClickable) onCardClick()
                    })
            }
        }
    }
}