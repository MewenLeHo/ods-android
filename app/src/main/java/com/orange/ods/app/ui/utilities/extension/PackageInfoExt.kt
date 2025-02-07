/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.utilities.extension

import android.content.pm.PackageInfo
import android.os.Build

@Suppress("DEPRECATION")
fun PackageInfo.versionCode(): Long =
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
        versionCode.toLong()
    } else {
        longVersionCode
    }
