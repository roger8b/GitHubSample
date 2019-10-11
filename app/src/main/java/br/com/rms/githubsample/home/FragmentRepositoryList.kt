package br.com.rms.githubsample.home

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
import kotlinx.android.synthetic.main.fragment_repository_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentRepositoryList : BaseFragment<FragmentRepositoryList.Listener>() {

    private val viewModel: RepositoryListViewModel by viewModel()

    private val repositories: List<Repository> by lazy {
        listOf<Repository>()
    }

    private val repositoryListAdapter: RepositoryListAdapter by lazy {
        RepositoryListAdapter()
    }

    private var page: Int = 1

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

    private fun updateUI(screenState: @ParameterName(name = "t") ScreenState<RepositoryListState>?) {
        when (screenState) {
            is ScreenState.ShowLoading -> repositoryListAdapter.addLoading()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: RepositoryListState) {
        when (renderState) {
            is RepositoryListState.ShowResult -> updateRepositoryList(renderState.result)
        }
    }

    private fun updateRepositoryList(result: List<Repository>) {
        logs.debug("UPDATE REPOSITORY LIST $result")
        repositoryListAdapter.run {
            addItems(result)
            notifyDataSetChanged()
        }
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
                    viewModel.fetchRepositoryList("language:kotlin", "stars", "desc", page++)
                }
            })
        }

        viewModel.fetchRepositoryList("language:kotlin", "stars", "desc", page)
    }

    interface Listener {}
}