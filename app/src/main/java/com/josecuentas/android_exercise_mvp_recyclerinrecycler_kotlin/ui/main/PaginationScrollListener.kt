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

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


/**
 * Created by jcuentas on 21/09/17.
 */
abstract class PaginationScrollListener : RecyclerView.OnScrollListener() {
    private var isLastPage = false
    var currentPage = 1
        private set
    private var isLoading = false

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val llm = recyclerView!!.layoutManager as LinearLayoutManager
        val visibleItemCount = llm.childCount
        val totalItemCount = llm.itemCount
        val firstVisibleItemPosition = llm.findFirstVisibleItemPosition()

        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                initSetup()

            }
        }
    }

    abstract fun loadMoreItems(currentPage: Int)

    private fun initSetup() {
        isLoading = true
        currentPage += 1
        loadMoreItems(this.currentPage)
    }

    fun setLoading(loading: Boolean) {
        isLoading = loading
    }

    fun resetPagination() {
        currentPage = 1
        isLastPage = false
    }

    fun lastPage() {
        this.isLastPage = true
    }

    companion object {

        private val PAGE_SIZE = 10
    }
}