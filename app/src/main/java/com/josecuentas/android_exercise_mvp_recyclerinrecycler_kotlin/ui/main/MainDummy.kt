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

package com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.ui.main

import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.Item
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.SubItem
import java.util.*

/**
 * Created by jcuentas on 21/09/17.
 */
class MainDummy {

    companion object {
        fun getItems(): List<Item> {


            val types = arrayListOf(
                    "people",       "transport",    "city",     "abstract",
                    "animals",      "food",         "nature",   "business",
                    "nightlife",    "sports",       "cats",     "fashion",
                    "technics"
            )
            val path = "http://lorempixel.com/400/200/"
            val itemList = ArrayList<Item>()
            val random = Random()
            (0..9).mapTo(itemList) {
                val nextInt = random.nextInt(13)
                val subItemList = ArrayList<SubItem>()
                val self = it
                (0..4).mapTo(subItemList) { SubItem(it, "$path/${types[self]}/$it") }
                Item(it, subItemList)
            }
            return itemList
        }
    }
}