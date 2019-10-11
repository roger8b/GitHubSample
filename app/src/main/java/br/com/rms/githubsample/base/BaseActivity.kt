package br.com.rms.githubsample.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.rms.githubsample.log.Logs
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    val logs: Logs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        onInitViews()
    }

    abstract fun onInitViews()

    abstract fun getContentView(): Int

    fun setVisibilityVisible(view: View) {
        view.visibility = View.VISIBLE
    }

    fun setVisibilityGone(view: View) {
        view.visibility = View.GONE
    }
}
