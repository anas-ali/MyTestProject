package com.example.test.utils

import com.example.test.data.models.ClassifiedItem
import java.text.SimpleDateFormat
import java.util.*

fun ClassifiedItem.getFormattedDate() : String {
    val serverTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = serverTimeFormat.parse ( this.created_at )

    val uiTimeFormat = SimpleDateFormat("HH:mm EEEE,MM yyyy", Locale.getDefault())
    return uiTimeFormat.format(date)
}