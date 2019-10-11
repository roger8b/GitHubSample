package br.com.rms.githubsample.base

import android.view.View
import androidx.fragment.app.Fragment
import br.com.rms.githubsample.log.Logs
import org.koin.android.ext.android.inject

abstract class BaseFragment<T> : Fragment() {

    val logs: Logs by inject()

    var listener: T? = null

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun setVisibilityVisible(view: View) {
        view.visibility = View.VISIBLE
    }

    fun setVisibilityGone(view: View) {
        view.visibility = View.GONE
    }


}
