package com.davaeth.tractee.android.ui.di

import com.davaeth.tractee.android.ui.timeDashboard.TimeDashboardViewModel
import com.davaeth.tractee.domain.Mapper
import com.davaeth.tractee.domain.mappers.ReschedulingTimerMapper
import com.davaeth.tractee.domain.useCases.CreateTimerUseCase
import com.davaeth.tractee.domain.useCases.DeleteTimerUseCase
import com.davaeth.tractee.domain.useCases.ListenForTimersUseCase
import com.davaeth.tractee.domain.useCases.UpdateTimerUseCase
import com.davaeth.tractee.repository.DriverFactory
import com.davaeth.tractee.repository.timer.TimerManager
import com.davaeth.tractee.utils.ExpectedReschedulingTimer
import data.TimerEntity
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import java.util.Timer

val repositoryModule = module {
    single { DriverFactory(get()) }
    single { TimerManager(get()) }
}

val domainMappersModule = module {
    factory<Mapper<TimerEntity, ExpectedReschedulingTimer<Timer>>> { ReschedulingTimerMapper() }
}

val domainUseCasesModule = module {
    factory { CreateTimerUseCase(get()) }
    factory { ListenForTimersUseCase(get(), get()) }
    factory { UpdateTimerUseCase(get()) }
    factory { DeleteTimerUseCase(get()) }
}

val uiModule = module {
    viewModelOf(::TimeDashboardViewModel)
}