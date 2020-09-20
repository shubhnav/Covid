package com.example.covid

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Country(val Country: String,
                   var CountryCode: String,
                   val Cases: Int,
                   var Date: Date
                   ) : Parcelable