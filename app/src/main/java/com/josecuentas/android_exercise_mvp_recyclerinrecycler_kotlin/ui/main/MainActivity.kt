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
import android.view.View
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.R
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.Item
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.Pagination
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.ui.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    val itemAdapter by lazy { ItemAdapter() }
    val presenter: MainPresenter by lazy { MainPresenter() }
    lateinit var listenerPagination: PaginationScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        injectPresenter()
        events()
        setup()
    }

    private fun events() {
        sreLayout.setOnRefreshListener { presenter.refresh() }
        listenerPagination = object : PaginationScrollListener() {
            override fun loadMoreItems(currentPage: Int) {
                presenter.getItems()
            }
        }
        rviContainer.addOnScrollListener(listenerPagination)
    }

    override fun onResume() {
        super.onResume()
        presenter.getItems()
    }

    private fun injectPresenter() {
        presenter.attached(this)
    }

    private fun setup() {
        setupRecycler()
    }

    private fun setupRecycler() {
        rviContainer.layoutManager = LinearLayoutManager(this)
        rviContainer.setHasFixedSize(false)
        rviContainer.adapter = itemAdapter
    }

    override fun showLoading() {
        pbaLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbaLoading.visibility = View.GONE
        listenerPagination.setLoading(false)
    }

    override fun hideRefreshLoading() {
        sreLayout.isRefreshing = false
    }

    override fun loadItems(itemList: List<Item>) {
        itemAdapter.itemList = itemList as ArrayList<Item>
        itemAdapter.notifyDataSetChanged()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            restoreState(savedInstanceState)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        saveState(outState)
        super.onSaveInstanceState(outState)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        val itemList: List<Item> = savedInstanceState.getSerializable(Item.BUNDLE_LIST) as List<Item>
        val pagination = savedInstanceState.getSerializable(Pagination.BUNDLE) as Pagination
        presenter.loadPresenterState(itemList, pagination)
        //restore adapter
        itemAdapter.itemList = itemList as ArrayList<Item>
        itemAdapter.notifyDataSetChanged()
    }

    private fun saveState(outState: Bundle?) {
        outState?.putSerializable(Item.BUNDLE_LIST, presenter.itemList as ArrayList)
        outState?.putSerializable(Pagination.BUNDLE, presenter.pagination)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyed()
    }

}
