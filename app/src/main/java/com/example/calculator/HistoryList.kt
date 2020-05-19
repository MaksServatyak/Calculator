package com.example.calculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class HistoryListAction(val listAction: MutableList<String>) : Parcelable
