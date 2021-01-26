package com.example.first_project

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamScore(val name: String, var score: Int) : Parcelable