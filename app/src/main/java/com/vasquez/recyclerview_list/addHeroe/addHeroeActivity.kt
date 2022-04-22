package com.vasquez.recyclerview_list.addHeroe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.vasquez.recyclerview_list.R
import com.google.android.material.textfield.TextInputEditText

const val HEROE_NAME = "name"
const val HEROE_DESCRIPTION = "description"


class AddHeroeActivity : AppCompatActivity() {
    private lateinit var addHeroeName: TextInputEditText
    private lateinit var addHeroDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_heroe_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addHeroe()
        }
        addHeroeName = findViewById(R.id.add_heroe_name)
        addHeroDescription = findViewById(R.id.add_heroe_description)
    }

    private fun addHeroe() {
        val resultIntent = Intent()

        if (addHeroeName.text.isNullOrEmpty() || addHeroDescription.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addHeroeName.text.toString()
            val description = addHeroDescription.text.toString()
            resultIntent.putExtra(HEROE_NAME, name)
            resultIntent.putExtra(HEROE_DESCRIPTION, description)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}