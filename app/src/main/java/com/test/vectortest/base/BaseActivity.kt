package com.test.vectortest.base

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.test.vectortest.R
import com.test.vectortest.di.activity.DaggerActivity
import com.test.vectortest.ui.utils.data.ResourceState
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.view_error.*

abstract class BaseActivity : DaggerActivity(), BaseView {

    var toolbar: Toolbar? =  null
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getLayoutId() == 0) {
            throw RuntimeException("Invalid Layout ID")
        }

        setContentView(R.layout.activity_base)

        view = layoutInflater.inflate(getLayoutId(), null)
        (view_base as FrameLayout).addView(view, LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))

        view_empty.emptyListener = checkAgain()
        view_error.errorListener = tryAgain()

        initializeToolbar()
    }

    private fun initializeToolbar() {}

    override fun onStop() {
        getScopePresenter().onStop()
        super.onStop()
    }

    abstract fun getScopePresenter(): ScopePresenter

    abstract fun getLayoutId(): Int

    override fun managementResourceState(resourceState: ResourceState, message: String?) {
        when (resourceState) {
            ResourceState.LOADING -> {
                view.visibility = VISIBLE
                view_error.visibility = GONE
                view_empty.visibility = GONE
                view_progress.visibility = VISIBLE
            }
            ResourceState.SUCCESS -> {
                view.visibility = VISIBLE
                view_error.visibility = GONE
                view_empty.visibility = GONE
                view_progress.visibility = GONE
            }
            ResourceState.EMPTY -> {
                view.visibility = GONE
                view_error.visibility = GONE
                view_empty.visibility = VISIBLE
                view_progress.visibility = GONE
            }
            ResourceState.ERROR -> {
                view.visibility = GONE
                view_error.visibility = VISIBLE
                error_message.text = message ?: ""
                view_empty.visibility = GONE
                view_progress.visibility = GONE
            }
        }
    }

    abstract fun checkAgain(): () -> Unit

    abstract fun tryAgain(): () -> Unit
}