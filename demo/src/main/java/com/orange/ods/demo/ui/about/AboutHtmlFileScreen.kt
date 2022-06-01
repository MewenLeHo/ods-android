/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.about

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.orange.ods.demo.R

private const val FILE_PATH = "file:///android_res/raw/"

@Composable
fun AboutHtmlFileScreen(
    aboutItemId: Long,
    updateTopBarTitle: (Int) -> Unit
) {
    val aboutItem = remember { aboutItems.firstOrNull { item -> item.id == aboutItemId } }

    aboutItem?.let { item ->
        updateTopBarTitle(item.titleRes)
        val context = LocalContext.current
        val configuration = LocalConfiguration.current
        val horizontalPadding = dimensionResource(id = R.dimen.ods_screen_horizontal_margin).value
        val verticalPadding = dimensionResource(id = R.dimen.ods_screen_vertical_margin).value
        AndroidView(
            factory = {
                WebView(context).apply {
                    @SuppressLint("SetJavaScriptEnabled")
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            view?.loadUrl("javascript:(function(){ document.body.style.padding = '${verticalPadding}px ${horizontalPadding}px' })();");
                        }
                    }
                    loadUrl("${FILE_PATH}${item.fileName}")
                    setBackgroundColor(Color.TRANSPARENT)
                    if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)) {
                        WebSettingsCompat.setForceDarkStrategy(settings, WebSettingsCompat.DARK_STRATEGY_WEB_THEME_DARKENING_ONLY)
                    }
                }
            },
            update = {
                if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                    val isDarkModeEnabled = configuration.uiMode and UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
                    val forceDarkMode = if (isDarkModeEnabled) WebSettingsCompat.FORCE_DARK_ON else WebSettingsCompat.FORCE_DARK_OFF
                    WebSettingsCompat.setForceDark(it.settings, forceDarkMode)
                }
            })
    }
}
