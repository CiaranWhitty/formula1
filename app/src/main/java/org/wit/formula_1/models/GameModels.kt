package org.wit.formula_1.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameModel(var gid: Long = 0,
                     var title: String = "",
                     var description: String = "") : Parcelable