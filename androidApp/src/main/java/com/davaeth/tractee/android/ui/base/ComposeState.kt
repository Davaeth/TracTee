package com.davaeth.tractee.android.ui.base

import kotlinx.coroutines.flow.StateFlow

interface ComposeState<T> {
    val state: StateFlow<T>
}