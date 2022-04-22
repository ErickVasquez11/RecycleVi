package com.vasquez.recyclerview_list.data

import android.content.res.Resources
import com.vasquez.recyclerview_list.R

fun heroeList(resources: Resources): List<Heroe> {
    return listOf(
        Heroe(
            id = 1,
            name = "Capitan America",
            image = R.drawable.ic_add_black_24dp,
            description = "hasiodhaskldhasdlk"
        ),
        Heroe(
            id = 2,
            name = "Iron Man",
            image = R.drawable.ic_add_black_24dp,
            description = "El mejor"
        )
    )
}