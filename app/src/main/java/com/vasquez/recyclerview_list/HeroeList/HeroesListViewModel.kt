package com.vasquez.recyclerview_list.HeroeList


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vasquez.recyclerview_list.data.DataSource
import com.vasquez.recyclerview_list.data.Heroe
import kotlin.random.Random

class HeroesListViewModel(val dataSource: DataSource) : ViewModel() {

    val heroesLiveData = dataSource.getHeroeList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    fun insertHeroe(heroeName: String?, heroeDescription: String?) {
        if (heroeName == null || heroeDescription == null) {
            return
        }

        val image = dataSource.getRandomHeroeImageAsset()
        val newHeroe = Heroe(
            Random.nextLong(),
            heroeName,
            image,
            heroeDescription
        )

        dataSource.addHeroe(newHeroe)
    }
}

class HeroesListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroesListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HeroesListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}