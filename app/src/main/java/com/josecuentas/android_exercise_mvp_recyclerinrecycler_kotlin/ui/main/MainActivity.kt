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

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.R
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.Item
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.ui.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val itemAdapter = ItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        rviContainer.layoutManager = LinearLayoutManager(this)
        rviContainer.setHasFixedSize(false)

        rviContainer.adapter = itemAdapter

        val itemList = ArrayList<Item>()
        (0..10).mapTo(itemList) { Item(it, emptyList()) }

        itemAdapter.itemList = itemList
        itemAdapter.notifyDataSetChanged()

        /*object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun getItemCount(): Int = 30

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                (holder.itemView as TextView).text = position.toString()
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

        }*/
    }
}
