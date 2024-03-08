package com.davaeth.tractee.android.ui.di

import com.davaeth.tractee.android.ui.timeDashboard.TimeDashboardViewModel
import com.davaeth.tractee.domain.useCases.CreateTimerUseCase
import com.davaeth.tractee.repository.DriverFactory
import com.davaeth.tractee.repository.timer.TimerManager
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val repositoryModule = module {
    single { DriverFactory(get()) }
    single { TimerManager(get()) }
}

val domainModule = module {
    factory { CreateTimerUseCase(get()) }
}

val uiModule = module {
    viewModelOf(::TimeDashboardViewModel)
}