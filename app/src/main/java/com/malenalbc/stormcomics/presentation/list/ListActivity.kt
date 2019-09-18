package com.malenalbc.stormcomics.presentation.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.malenalbc.stormcomics.R
import com.malenalbc.stormcomics.core.exAppApptension.viewModel
import com.malenalbc.stormcomics.core.extension.observe
import com.malenalbc.stormcomics.core.extension.showImage
import com.malenalbc.stormcomics.data.model.comic.Comic
import com.malenalbc.stormcomics.model.CharacterEntity
import com.malenalbc.stormcomics.model.ComicEntity
import com.malenalbc.stormcomics.model.DataWrapper
import com.malenalbc.stormcomics.presentation.BaseActivity
import com.malenalbc.stormcomics.presentation.detail.DetailActivity
import com.malenalbc.stormcomics.presentation.detail.DetailTabletFragment
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import java.util.*


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [DetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ListActivity : BaseActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        viewModel = viewModel(viewModelFactory) {
            observe(data, { handleResponse(it) })
            observe(header, { handleHeaderResponse(it) })
        }
        viewModel.loadData()
    }

    private fun handleHeaderResponse(wrapper: DataWrapper<CharacterEntity>) {
        when (wrapper) {
            is DataWrapper.Success -> wrapper.data?.let { populateHeader(it) } ?: handleEmptyState()
            else -> {/*Do nothing, the header is not important*/}
        }
    }

    private fun populateHeader(character: CharacterEntity) {
        toolbar_layout.title = getString(R.string.app_name);
        img_character.showImage(character.thumbnail)
        txt_description.text = character.description
    }

    private fun handleResponse(wrapper: DataWrapper<List<ComicEntity>>) {
        when (wrapper) {
            is DataWrapper.Success -> wrapper.data?.let { populateList(it) } ?: handleEmptyState()
            is DataWrapper.Loading -> showLoading()
            is DataWrapper.Error -> handleError(wrapper.error)
            is DataWrapper.Empty -> handleEmptyState()
        }
    }

    private fun showLoading() {
        swipe_refresh?.isRefreshing = true
    }

    private fun hideLoading() {
        swipe_refresh?.isRefreshing = false
    }

    private fun handleError(error: Throwable?) {
        hideLoading()
        val message = error?.localizedMessage?.let { "ERROR: $it" } ?: getString(R.string.error_generic)
        Snackbar.make(frameLayout, message, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun handleEmptyState() {
        // TODO
    }

    private fun populateList(items: List<ComicEntity>) {
        hideLoading()
        val onItemClick: (ComicEntity, View) -> Unit = { item, view ->
            if (twoPane) {
                openTabletDetail(item)
            } else {
                openDetail(item, view)
            }
        }

        item_list.adapter = ComicsAdapter(items, onItemClick)
    }

    private fun getRandomImageUrl(comic: Comic): String {
        val images = comic.images
        return if (!images.isNullOrEmpty()) {
            val randomNum = Random().nextInt(images.size)
            images[randomNum].url
        } else {
            ""
        }
    }

    private fun openDetail(item: ComicEntity, view: View) {
        val intent = Intent(view.context, DetailActivity::class.java).apply {
            putExtra(DetailTabletFragment.ARG_ITEM, item.id)
        }
        view.context.startActivity(intent)
    }

    private fun openTabletDetail(item: ComicEntity) {
        val fragment = DetailTabletFragment().apply {
            arguments = Bundle().apply {
                putString(DetailTabletFragment.ARG_ITEM, item.id.toString())
            }
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.item_detail_container, fragment)
            .commit()
    }


}
