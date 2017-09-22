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

import android.os.Handler
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.Item
import com.josecuentas.android_exercise_mvp_recyclerinrecycler_kotlin.domain.model.Pagination

/**
 * Created by jcuentas on 21/09/17.
 */
class MainPresenter : MainContract.Presenter, MainContract.Listener {

    private var view: MainContract.View? = null
    val itemList: MutableList<Item> = ArrayList()
    var pagination = Pagination()

    override fun attached(view: MainContract.View) {
        this.view = view
    }

    override fun detached() {
        this.view = null
    }

    override fun destroyed() {
        detached()
    }

    override fun getItems() {
        if (pagination.isEnd()) {
            //this.view?.loadItems(itemList)
            this.view?.hideLoading()
            return
        }

        this.view?.showLoading()
        //simulation service
        Handler().postDelayed({
            val nextPage = pagination.getNextPage()
            if (pagination.currentPage <= pagination.lastPage)
                itemList.addAll(MainDummy.getItems())

            this.view?.loadItems(itemList)
            this.view?.hideLoading()
        }, 400)
    }

    override fun refresh() {
        this.reset()
        val nextPage = pagination.getNextPage()
        this.itemList.addAll(MainDummy.getItems())
        this.view?.loadItems(itemList)
        this.view?.hideRefreshLoading()
    }

    private fun reset() {
        this.itemList.clear()
        this.pagination.reset()
    }

    override fun loadPresenterState(itemList: List<Item>?, pagination: Pagination) {
        if (itemList != null) {
            this.itemList.addAll(itemList)
        }
        this.pagination = pagination
    }
}