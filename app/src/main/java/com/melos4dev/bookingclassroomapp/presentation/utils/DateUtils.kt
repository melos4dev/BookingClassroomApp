package com.melos4dev.bookingclassroomapp.presentation.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    fun convertDateToString(date: Date): String {
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(date)
    }

    fun convertDateMillisToString(selectedDateMillis: Long): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date(selectedDateMillis)
        return dateFormat.format(date)
    }

    fun getDayOfWeek(dateString: String): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        /*
                val calendar = Calendar.getInstance()
                calendar.time = date
                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                val dayOfWeekName = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date).lowercase()*/

        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(date!!).lowercase()

    }

    fun convertStringToDate(fechaString: String): Date? {
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.parse(fechaString)
    }

   /* fun getDate(): String {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return "$day/$month/$year"
    }*/

    fun getCurrentDate(): Date {
        val currentDate = Date()
        val format = SimpleDateFormat("dd/MM/yyyy")
        val formatDate = format.format(currentDate)

        return format.parse(formatDate)
    }

    fun isDateBeforeYesterday(date: Date): Boolean {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -1) // Subtract 1 day from the current date

        val yesterday = cal.time // Get the date for yesterday

        return date.before(yesterday) // Check if the provided date is before yesterday
    }
}