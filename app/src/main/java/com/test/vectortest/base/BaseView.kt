package com.test.vectortest.base

import com.test.vectortest.ui.data.ResourceState

interface BaseView {
    fun managementResourceState(resourceState: ResourceState, message: String? = null)
}