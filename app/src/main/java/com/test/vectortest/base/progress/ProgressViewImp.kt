package com.test.vectortest.base.progress

import android.app.ProgressDialog

class ProgressViewImp(private var progressDialog: ProgressDialog): IProgressView {

    override fun showProgress(message: String) {
        progressDialog.run {
            setMessage(message)
            show()
        }
    }

    override fun dismissProgress() {
        progressDialog.dismiss()
    }
}