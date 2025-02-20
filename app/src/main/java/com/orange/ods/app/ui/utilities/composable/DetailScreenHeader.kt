/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.utilities.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.Component

@Composable
fun DetailScreenHeader(
    @DrawableRes imageRes: Int,
    imageAlignment: Alignment = Alignment.Center,
    @StringRes descriptionRes: Int
) {
    Image(
        painter = painterResource(id = imageRes),
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .background(Color(Component.ImageBackgroundColor)),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        alignment = imageAlignment
    )
    DetailScreenDescription(
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)
        ),
        descriptionRes = descriptionRes
    )
}

@Composable
private fun DetailScreenDescription(
    modifier: Modifier = Modifier,
    @StringRes descriptionRes: Int
) {
    OdsTextBody1(
        text = stringResource(descriptionRes),
        modifier = modifier.padding(
            top = dimensionResource(id = R.dimen.spacing_m)
        )
    )
}