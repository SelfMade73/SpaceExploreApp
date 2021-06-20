package com.example.spaceinfo.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity( tableName = "nasa_cards" )
data class ThemeItem(
        @PrimaryKey(autoGenerate = true)
        var id : Long,
        val url : String,
        val title: String,
        val explanation: String,
        val date: String
) : Parcelable{
        fun getTruncatedExplanation() : String = explanation.take(140) + " ..."
}