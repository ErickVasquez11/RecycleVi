package com.vasquez.recyclerview_list.HeroeList


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vasquez.recyclerview_list.R
import com.vasquez.recyclerview_list.addHeroe.AddHeroeActivity
import com.vasquez.recyclerview_list.addHeroe.HEROE_DESCRIPTION
import com.vasquez.recyclerview_list.addHeroe.HEROE_NAME
import com.vasquez.recyclerview_list.data.Heroe
import com.vasquez.recyclerview_list.heroeDatail.HeroeMainActivity


const val HEROE_ID = "flower id"

class HeroesListActivity : AppCompatActivity() {
    private val newHeroeActivityRequestCode = 1

    private val heroesListViewModel by viewModels<HeroesListViewModel> {
        HeroesListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and flowersAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val heroesAdapter = HeroesAdapter { heroe -> adapterOnClick(heroe) }
        val concatAdapter = ConcatAdapter(headerAdapter, heroesAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recyvler_view)
        recyclerView.adapter = concatAdapter

        heroesListViewModel.heroesLiveData.observe(this, {
            it?.let {
                heroesAdapter.submitList(it as MutableList<Heroe>)
                headerAdapter.updateHeroeCount(it.size)
            }
        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    /* Opens FlowerDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(heroe: Heroe) {
        val intent = Intent(this, HeroeMainActivity()::class.java)
        intent.putExtra(HEROE_ID, heroe.id)
        startActivity(intent)
    }

    /* Adds flower to flowerList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddHeroeActivity::class.java)
        startActivityForResult(intent, newHeroeActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts flower into viewModel. */
        if (requestCode == newHeroeActivityRequestCode && resultCode == RESULT_OK) {
            intentData?.let { data ->
                val heroeName = data.getStringExtra(HEROE_NAME)
                val heroeDescription = data.getStringExtra(HEROE_DESCRIPTION)

                heroesListViewModel.insertFlower(heroeName, heroeDescription)
            }
        }
    }
}

