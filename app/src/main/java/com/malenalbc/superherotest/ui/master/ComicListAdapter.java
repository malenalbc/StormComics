package com.malenalbc.superherotest.ui.master;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.malenalbc.superherotest.R;
import com.malenalbc.superherotest.data.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

class ComicListAdapter extends RecyclerView.Adapter<ComicListAdapter.CardViewHolder> {
    private final List<Comic> comics;
    private final CardViewHolder.ComicClickListener clickListener;

    ComicListAdapter (List<Comic> comics, CardViewHolder.ComicClickListener clickListener) {
        this.comics = comics;
        this.clickListener = clickListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comic_list_card, parent, false);
        return new CardViewHolder(cardView, clickListener);
    }

    @Override
    public void onBindViewHolder (CardViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.comic = comic;

        ImageView layoutImageView = holder.comicImage;
        Context context = layoutImageView.getContext();
        Picasso.with(context)
                .load(comic.thumbnail.getUrl())
                .resize(300, 600)
                .centerInside()
                .into(layoutImageView);
        holder.comicTitle.setText(comic.title);
        holder.comicIssue.setText(
                String.format(context.getString(R.string.issue_number), comic.issueNumber));
        holder.comicPages.setText(
                String.format(context.getString(R.string.pages_number), comic.pageCount));
    }

    @Override
    public int getItemCount () {
        return comics.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Comic comic;
        ComicClickListener listener;

        ImageView comicImage;
        TextView comicTitle;
        TextView comicIssue;
        TextView comicPages;

        @Override
        public void onClick (View view) {
            listener.onClick(comic);
        }

        CardViewHolder (View view, ComicClickListener listener) {
            super(view);
            this.comicTitle = (TextView) view.findViewById(R.id.txtComicTitle);
            this.comicImage = (ImageView) view.findViewById(R.id.imgComic);
            this.comicIssue = (TextView) view.findViewById(R.id.txtIssueNumber);
            this.comicPages = (TextView) view.findViewById(R.id.txtPageCount);

            this.listener = listener;
            view.setOnClickListener(this);
        }

        public interface ComicClickListener {
            void onClick (Comic comic);
        }
    }


}
