package br.com.rms.githubsample.home

import br.com.rms.githubsample.R
import br.com.rms.githubsample.base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun getContentView(): Int = R.layout.activity_home

    override fun onInitViews() {
        logs.debug("HOME ACTIVITY STARTED")
    }
}
