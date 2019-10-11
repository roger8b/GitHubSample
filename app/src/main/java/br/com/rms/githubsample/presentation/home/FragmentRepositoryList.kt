package br.com.rms.githubsample.presentation.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import br.com.rms.githubsample.R
import br.com.rms.githubsample.base.BaseFragment
import br.com.rms.githubsample.base.PaginationScrollListener
import br.com.rms.githubsample.base.ScreenState
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.presentation.viewmodel.SearchRepositoryViewModel
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentRepositoryList : BaseFragment<FragmentRepositoryList.Listener>() {

    private val viewModel: SearchRepositoryViewModel by viewModel()

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

        viewModel.state.observe(::getLifecycle, ::updateUI)

        setupRecyclerView()
    }

    private fun updateUI(screenState: @ParameterName(name = "t") ScreenState<SearchRepositoryViewModel.State>?) {
        when (screenState) {
            is ScreenState.ShowLoading -> repositoryListAdapter.addLoading()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: SearchRepositoryViewModel.State) {
        when (renderState) {
            is SearchRepositoryViewModel.State.ShowResult -> updateRepositoryList(renderState.result)
            is SearchRepositoryViewModel.State.ShowError -> showErrorMessage()
        }
    }

    private fun updateRepositoryList(result: List<Repository>) {
        logs.debug("UPDATE REPOSITORY LIST $result")
        repositoryListAdapter.run {
            addItems(result)
            notifyDataSetChanged()
        }
    }

    private fun showErrorMessage() {
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage(getString(R.string.alert_dialog_error_message))
            .setPositiveButton(getString(R.string.alert_dialog_error_button_yes)) { _, _ ->

                viewModel.fetchRepositoryList("language:kotlin", "stars", "desc", page)

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
        repositoryListAdapter
            .addItems(repositories)
            .setListener {
                logs.debug("REPOSITORY ITEM HAS CLICKED")
            }

        val gridLayoutManager = GridLayoutManager(context, 1)
        repositoryList.run {
            layoutManager = gridLayoutManager
            adapter = repositoryListAdapter
            setHasFixedSize(true)
            addOnScrollListener(object : PaginationScrollListener(gridLayoutManager) {
                override fun loadMoreItems() {
                    if (page >= 1)
                        viewModel.fetchRepositoryList("language:kotlin", "stars", "desc", page++)
                }
            })
        }

        viewModel.fetchRepositoryList("language:kotlin", "stars", "desc", page++)
    }

    interface Listener {}
}