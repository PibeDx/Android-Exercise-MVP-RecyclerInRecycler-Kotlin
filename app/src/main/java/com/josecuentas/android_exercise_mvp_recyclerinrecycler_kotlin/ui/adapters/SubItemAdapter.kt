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

package com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.R
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.SubItem
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.ui.adapters.holder.SubItemViewHolder

/**
 * Created by jcuentas on 20/09/17.
 */
class SubItemAdapter: RecyclerView.Adapter<SubItemViewHolder>() {

    var subItemList = ArrayList<SubItem>()

    override fun onBindViewHolder(holder: SubItemViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_sub_item, parent, false)
        return SubItemViewHolder(view)
    }

    override fun getItemCount(): Int = subItemList.size
}