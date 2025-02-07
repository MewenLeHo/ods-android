/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.domain.datastore

interface DataStoreService {

    suspend fun putString(key: String, value: String)

    suspend fun getString(key: String): String?
}