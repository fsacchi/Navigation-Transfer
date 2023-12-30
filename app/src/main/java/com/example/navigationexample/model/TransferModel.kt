package com.example.navigationexample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransferModel(
    val namePeople: String,
    var valueTransfer: Double = 0.0,
    var validated : Boolean = false
): Parcelable
