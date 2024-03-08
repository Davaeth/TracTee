package com.davaeth.tractee.repository.timer

import com.davaeth.tractee.repository.DriverFactory
import com.davaeth.tractee.repository.createDatabase
import data.Timer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class TimerManager(private val driverFactory: DriverFactory) {
    suspend fun insertTimer() =
        withContext(Dispatchers.IO) {
            val database = createDatabase(driverFactory)
            database.timerQueries.insertTimer(0.0, null)
        }

    suspend fun getNewestTimer(): Result<Timer> = withContext(Dispatchers.IO) {
        val database = createDatabase(driverFactory)
        database.timerQueries.getNewestTimer().executeAsOneOrNull()?.let { Result.success(it) }
            ?: Result.failure(NullPointerException("There are no timer in the database!"))
    }
}