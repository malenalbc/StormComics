package com.malenalbc.stormcomics.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.malenalbc.stormcomics.R
import com.malenalbc.stormcomics.model.ComicEntity
import kotlinx.android.synthetic.main.activity_item_detail.toolbar_layout
import kotlinx.android.synthetic.main.item_detail.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [DetailActivity]
 * on handsets.
 */
class DetailTabletFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            val comic: ComicEntity? = bundle.getParcelable(ARG_ITEM)
            comic?.let {
                showComicDetails(it)
            } ?: showError()
        }
    }

    private fun showError() {
        Snackbar.make(comic_description_container, R.string.error_generic, Snackbar.LENGTH_LONG)
        comic_description_container?.post {
            activity?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_detail, container, false)
    }

    private fun showComicDetails(comic: ComicEntity) {
        activity?.toolbar_layout?.title = comic.title

    }

    companion object {
        const val ARG_ITEM = "item.comic"
    }
}
