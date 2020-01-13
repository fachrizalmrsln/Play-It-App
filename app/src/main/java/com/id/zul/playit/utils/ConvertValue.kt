package com.id.zul.playit.utils

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ConstantLocale")
object ConvertDate {
    private val defaultFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val onlyYear = SimpleDateFormat("yyyy", Locale.getDefault())
    private val IDFormat = SimpleDateFormat("EE, dd MMMM yyyy", Locale.getDefault())

    fun dateToOnlyYear(data: String): String {
        val date = defaultFormat.parse(data)!!
        return onlyYear.format(date)
    }

    fun dateToIDFormat(data: String): String {
        val date = defaultFormat.parse(data)!!
        return IDFormat.format(date)
    }

}

object ConvertDecimal {
    private val decimalFormat = DecimalFormat("#.#")

    fun rateToOnlyOneAfterComa(data: Double): String {
        return decimalFormat.format(data).toString()
    }

}