package com.alrozhan.valorantagent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent (
    val name: String,
    val role: String,
    val icon: Int,
    val biography: String,
    val roleicon: Int,
    val banner: Int
):Parcelable
