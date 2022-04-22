package com.vasquez.recyclerview_list.heroeDatail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vasquez.recyclerview_list.data.DataSource
import com.vasquez.recyclerview_list.data.Heroe


class HeroeDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a flower that corresponds to an id. */
    fun getHeroeForId(id: Long) : Heroe? {
        return datasource.getHeroeForId(id)
    }

    /* Queries datasource to remove a flower. */
    fun removeHeroe(heroe: Heroe) {
        datasource.removeHeroe(heroe)
    }
}

class HeroeDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroeDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HeroeDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}