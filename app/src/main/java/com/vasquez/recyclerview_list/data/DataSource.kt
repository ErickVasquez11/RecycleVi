package com.vasquez.recyclerview_list.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/* Handles operations on flowersLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialHeroeList = heroeList(resources)
    private val heroeLiveData = MutableLiveData(initialHeroeList)

    /* Adds flower to liveData and posts value. */
    fun addHeroe(heroe: Heroe) {
        val currentList = heroeLiveData.value
        if (currentList == null) {
            heroeLiveData.postValue(listOf(heroe))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, heroe)
            heroeLiveData.postValue(updatedList)
        }
    }

    /* Removes flower from liveData and posts value. */
    fun removeHeroe(heroe: Heroe) {
        val currentList = heroeLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(heroe)
            heroeLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getHeroeForId(id: Long): Heroe? {
        heroeLiveData.value?.let { heroe ->
            return heroe.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getHeroeList(): LiveData<List<Heroe>> {
        return heroeLiveData
    }

    /* Returns a random flower asset for flowers that are added. */
    fun getRandomHeroeImageAsset(): Int? {
        val randomNumber = (initialHeroeList.indices).random()
        return initialHeroeList[randomNumber].image
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}