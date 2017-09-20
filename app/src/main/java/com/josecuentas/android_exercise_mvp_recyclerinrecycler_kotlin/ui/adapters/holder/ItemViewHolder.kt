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

package com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.ui.adapters.holder

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.R
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.SubItem
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.ui.adapters.SubItemAdapter


/**
 * Created by jcuentas on 20/09/17.
 */
class ItemViewHolder : RecyclerView.ViewHolder {
    val rviItem: RecyclerView

    constructor(itemView: View) : super(itemView) {
        rviItem = itemView.findViewById(R.id.rviItem)
    }

    fun setSubItems(subItemList: List<SubItem>) {
        if (rviItem.adapter == null) {
            val subItemAdapter = SubItemAdapter()
            subItemAdapter.subItemList = subItemList as ArrayList<SubItem>
            val linearLayoutManager = LinearLayoutManager(rviItem.context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rviItem.layoutManager = linearLayoutManager
            rviItem.setHasFixedSize(true)
            rviItem.adapter = subItemAdapter
        } else {
            val subItemAdapter = rviItem.adapter as SubItemAdapter
            subItemAdapter.subItemList = subItemList as ArrayList<SubItem>
            subItemAdapter.notifyDataSetChanged()
        }
    }
}