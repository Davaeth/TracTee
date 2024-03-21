package com.davaeth.tractee.repository.timer

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.davaeth.Database
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.domain.common.databaseValue
import com.davaeth.tractee.repository.DriverFactory
import com.davaeth.tractee.repository.createDatabase
import data.TimerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TimerManager(driverFactory: DriverFactory) {
    private val database: Database = createDatabase(driverFactory)

    val timersListener: Flow<List<TimerEntity>>
        get() = database
            .timerQueries
            .listenForTimers()
            .asFlow()
            .mapToList(Dispatchers.IO)

    suspend fun insertTimer(): Unit =
        withContext(Dispatchers.IO) {
            database.timerQueries.insertTimer(0, null)
        }

    suspend fun updateTimer(time: Long, title: String, id: Id): Result<Unit> =
        withContext(Dispatchers.IO) {
            runCatching {
                database
                    .timerQueries
                    .updateTimer(time, title, id.databaseValue)
            }
        }

    suspend fun deleteTimer(id: Id) = withContext(Dispatchers.IO) {
        runCatching {
            database
                .timerQueries
                .deleteTimer(id.databaseValue)
        }
    }
}