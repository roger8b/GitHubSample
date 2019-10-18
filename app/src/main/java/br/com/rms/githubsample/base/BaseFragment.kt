package br.com.rms.githubsample.base

import android.view.View
import androidx.fragment.app.Fragment
import br.com.rms.githubsample.log.Logs
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {

    val logs: Logs by inject()

    fun setVisibilityVisible(view: View) {
        view.visibility = View.VISIBLE
    }

    fun setVisibilityGone(view: View) {
        view.visibility = View.GONE
    }


}
