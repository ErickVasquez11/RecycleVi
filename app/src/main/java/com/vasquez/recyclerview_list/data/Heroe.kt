package com.vasquez.recyclerview_list.data

import androidx.annotation.DrawableRes

data class Heroe (

    val id: Long,
            val name: String,
                    @DrawableRes
                    val image: Int?,
                            val description: String
)