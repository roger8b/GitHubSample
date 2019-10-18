package br.com.rms.githubsample.base

import android.os.Bundle
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

}
