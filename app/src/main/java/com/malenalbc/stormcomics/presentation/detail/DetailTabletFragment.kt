package com.malenalbc.stormcomics.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.malenalbc.stormcomics.R
import com.malenalbc.stormcomics.core.extension.showImage
import com.malenalbc.stormcomics.model.ComicEntity
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.*


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [DetailActivity]
 * on handsets.
 */
class DetailTabletFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let { bundle ->
            val comic: ComicEntity? = bundle.getParcelable(ARG_ITEM)
            comic?.let {
                showComicDetails(it)
            } ?: showError()
        }
    }

    private fun showComicDetails(comic: ComicEntity) {
        activity?.apply {
            toolbar_layout?.title = comic.title
            cover?.showImage(comic.headerUrl)
            fab_link?.setOnClickListener {
                comic.url?.let {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                    startActivity(browserIntent)
                }
            }
        }

        comic_detail.text = comic.description
    }

    private fun showError() {
        activity?.apply {
            Snackbar.make(item_detail_container, R.string.error_generic, Snackbar.LENGTH_LONG)
            item_detail_container?.postDelayed({
                onBackPressed()
            }, 2000)
        }
    }

    companion object {
        const val ARG_ITEM = "item.comic"
    }
}
