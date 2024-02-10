package com.davaeth.tractee.repository.timer

import com.davaeth.tractee.repository.DriverFactory
import com.davaeth.tractee.repository.createDatabase

class TimerManager(private val driverFactory: DriverFactory) {
    fun insertTimer() {
        val database = createDatabase(driverFactory)
        database.timerQueries.insertTimer(0.0, null)
    }
}