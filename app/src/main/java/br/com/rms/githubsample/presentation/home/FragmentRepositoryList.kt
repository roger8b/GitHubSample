package br.com.rms.githubsample.presentation.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.githubsample.R
import br.com.rms.githubsample.base.BaseFragment
import br.com.rms.githubsample.base.EndlessRecyclerViewScrollListener
import br.com.rms.githubsample.base.ScreenState
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.presentation.viewmodel.RepositoryListViewModel
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentRepositoryList : BaseFragment<FragmentRepositoryList.Listener>() {

    private var scrollListener: EndlessRecyclerViewScrollListener? = null

    private var layoutManager: LinearLayoutManager? = null

    private val listViewModel: RepositoryListViewModel by viewModel()


    private val repositories: List<Repository> by lazy {
        listOf<Repository>()
    }

    private val repositoryListAdapter: RepositoryListAdapter by lazy {
        RepositoryListAdapter()
    }

    private var page: Int = 0

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logs.debug("FRAGMENT REPOSITORY LIST STARTED")

        listViewModel.state.observe(::getLifecycle, ::updateUI)

        setupRecyclerView()
    }

    private fun updateUI(screenState: @ParameterName(name = "t") ScreenState<RepositoryListViewModel.State>?) {
        when (screenState) {
            is ScreenState.ShowLoading -> showLoading()
            is ScreenState.HideLoading -> hideLoading()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun showLoading() {
        repositoryListAdapter.addLoading()
        scrollListener?.onLoading()
    }

    private fun hideLoading() {
        repositoryListAdapter.removeLoading()
        scrollListener?.hideLoading()
    }

    private fun processRenderState(renderState: RepositoryListViewModel.State) {
        when (renderState) {
            is RepositoryListViewModel.State.UpdateRepositoryList -> updateRepositoryList(renderState.result)
            is RepositoryListViewModel.State.ShowError -> showErrorMessage()
        }
    }

    private fun updateRepositoryList(result: List<Repository>) {
        logs.debug("UPDATE REPOSITORY LIST ADD NEW ${result.size} ITENS")
        repositoryListAdapter.run {
            addItems(result)
            notifyDataSetChanged()
        }
    }

    private fun showErrorMessage() {
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage(getString(R.string.alert_dialog_error_message))
            .setPositiveButton(getString(R.string.alert_dialog_error_button_yes)) { _, _ ->

                listViewModel.fetchRepositoryList("language:kotlin", "stars", "desc", page)

            }
            .setNegativeButton(getString(R.string.alert_dialog_error_button_not)) { _, _ ->

                repositoryListAdapter.addItems(emptyList())
                dialog.setCancelable(true)
                activity?.finish()
            }
            .create()
            .show()
    }

    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(context)

        repositoryList.layoutManager = layoutManager
        repositoryList.adapter = repositoryListAdapter
        repositoryList.setHasFixedSize(true)

        repositoryListAdapter
            .addItems(repositories)
            .setListener { logs.debug("REPOSITORY ITEM HAS CLICKED") }

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager!!) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

                this@FragmentRepositoryList.page = page

                logs.debug("RECYCLER VIEW ON SCROLL LISTENER LOAD MORE: PAGE: $page TOTAL ITENS COUNT: $totalItemsCount ")

                listViewModel.fetchRepositoryList("language:kotlin", "stars", "desc", page)
            }
        }

        repositoryList.addOnScrollListener(scrollListener as EndlessRecyclerViewScrollListener)



        if (repositoryListAdapter.itemCount == 0) {
            listViewModel.fetchRepositoryList("language:kotlin", "stars", "desc", page)
        }
    }


    interface Listener
}