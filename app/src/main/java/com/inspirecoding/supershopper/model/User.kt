package com.inspirecoding.supershopper.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var name: String = "",
    var numberOfFriends: Int = 0,
    var profilePicture: String = ""
) : Parcelable
