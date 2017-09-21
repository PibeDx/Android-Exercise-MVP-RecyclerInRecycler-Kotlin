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

/**
 * Created by jcuentas on 21/09/17.
 */
class MainPresenter : MainContract.Presenter, MainContract.Listener {

    private var view: MainContract.View? = null
    val itemList: MutableList<Item> = ArrayList()

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
        this.view?.showLoading()
        if (this.itemList.isEmpty()) {
            itemList.addAll(MainDummy.getItems())
        }
        this.view?.loadItems(itemList)
        this.view?.hideLoading()
    }

    override fun refresh() {
        this.itemList.clear()
        this.itemList.addAll(MainDummy.getItems())

        this.view?.loadItems(itemList)

        this.view?.hideRefreshLoading()
    }

    override fun loadPresenterState(itemList: List<Item>?) {
        if (itemList != null) {
            this.itemList.addAll(itemList)
        }
    }
}