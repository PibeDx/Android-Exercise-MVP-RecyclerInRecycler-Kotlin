/**
 * Copyright 2017, José Norberto Cuentas Turpo.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model

import java.io.Serializable

/**
 * Created by jcuentas on 21/09/17.
 */
class Pagination : Serializable {

    companion object {
        val BUNDLE = ".pagination"
        val LAST_PAGE = 3
    }

    val total: Int = 0
    val perPage: Int = 0
    var currentPage: Int = 0
    val lastPage: Int = LAST_PAGE
    val nextPageUrl: String? = null
    val prevPageUrl: String? = null
    val from: Int = 0
    val to: Int = 0

    fun getNextPage(): Int {
        if (currentPage <= LAST_PAGE) {
            currentPage += 1
        }
        return currentPage
    }

    fun reset() {
        currentPage = 0
    }

    fun isEnd(): Boolean {
        if (currentPage >= lastPage) return true
        return false
    }
}