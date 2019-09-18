package com.malenalbc.stormcomics.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.malenalbc.stormcomics.R
import com.malenalbc.stormcomics.core.extension.showImage
import com.malenalbc.stormcomics.model.ComicEntity
import kotlinx.android.synthetic.main.item_list_content.view.*

class ComicsAdapter(
    private val values: List<ComicEntity>,
    private val onItemClick: (ComicEntity, View) -> Unit
) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as ComicEntity
            onItemClick(item, v)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.coverView.showImage(item.thumbnailUrl)
        holder.titleView.text = item.title
        holder.contentView.text = item.description

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coverView: ImageView = view.cover
        val titleView: TextView = view.title
        val contentView: TextView = view.description
    }
}
