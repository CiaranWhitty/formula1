package org.wit.formula_1.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerModel(var pid: Long = 0,
                       var name: String = "",
                       var points: String = ""): Parcelable
