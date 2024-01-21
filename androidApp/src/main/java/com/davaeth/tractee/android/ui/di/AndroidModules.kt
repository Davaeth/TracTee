package com.davaeth.tractee.android.ui.di

import com.davaeth.tractee.android.ui.timeDashboard.TimeDashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::TimeDashboardViewModel)
}