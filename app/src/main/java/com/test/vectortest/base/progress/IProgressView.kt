package com.test.vectortest.base.progress

interface IProgressView {
    fun showProgress(message: String)
    fun dismissProgress()
}