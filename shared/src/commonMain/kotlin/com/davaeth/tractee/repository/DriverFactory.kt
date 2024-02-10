package com.davaeth.tractee.repository

import app.cash.sqldelight.db.SqlDriver
import com.davaeth.Database

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    return Database(driver)
}

const val DATABASE_NAME = "tractee.db"