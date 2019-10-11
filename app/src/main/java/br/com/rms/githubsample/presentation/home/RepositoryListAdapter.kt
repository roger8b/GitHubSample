package br.com.rms.githubsample.presentation.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.githubsample.R
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.ext.inflate
import kotlinx.android.synthetic.main.adapter_repository_list_item.view.*

class RepositoryListAdapter :
    RecyclerView.Adapter<RepositoryListAdapter.BaseViewHolder>() {

    private val itemList: MutableList<Pair<Repository, Boolean>> by lazy {
        mutableListOf<Pair<Repository, Boolean>>()
    }

    private var listener: (Repository.() -> Unit)? = null

    fun addLoading() {
        if (itemList.isEmpty()) {
            itemList.run {
                add(emptyRepository())
                notifyItemInserted(lastIndex)
            }
        } else if (!itemList.last().second) {
            itemList.run {
                add(lastIndex + 1, emptyRepository())
                notifyItemInserted(lastIndex)
            }
        }
    }

    private fun removeLoading() {
        itemList.map {
            if (it.second) {
                val indexOf = itemList.indexOf(it)
                itemList.removeAt(indexOf)
                notifyItemRemoved(indexOf)
            }
        }
    }

    private fun emptyRepository() = Pair(Repository("", "", "", 0, 0, ""), true)


    fun addItems(list: List<Repository>) = apply {
        removeLoading()
        list.map {
            itemList.add(Pair(it, false))
        }
        notifyDataSetChanged()
    }

    fun setListener(listener: (Repository.() -> Unit)) = apply {
        this.listener = listener
    }

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            VIEW_TYPE_NORMAL -> ViewHolder(parent.inflate(R.layout.adapter_repository_list_item))
            VIEW_TYPE_LOADING -> ViewHolderLoading(parent.inflate(R.layout.adapter_repository_list_louder))
            else -> throw IllegalAccessException("View Type not Implemented")
        }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(itemList.elementAt(position), listener)
    }

    override fun getItemViewType(position: Int): Int =
        if (itemList[position].second) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL

    class ViewHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun bind(pair: Pair<Repository, Boolean>, listener: (Repository.() -> Unit)?) =
            with(itemView) {
                val repository = pair.first

                title.text = repository.title
                description.text = repository.description
                pullRequestCounter.text = repository.pullRequestCounter.toString()
                repositoryStarCounter.text = repository.starCounter.toString()

                if (listener != null) setOnClickListener { listener(repository) }
            }
    }

    class ViewHolderLoading(itemView: View) : BaseViewHolder(itemView)

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        open fun bind(pair: Pair<Repository, Boolean>, listener: (Repository.() -> Unit)?) =
            run { }
    }
}

const val VIEW_TYPE_NORMAL = 0
const val VIEW_TYPE_LOADING = 1