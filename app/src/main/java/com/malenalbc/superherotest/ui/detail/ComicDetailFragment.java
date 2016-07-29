package com.malenalbc.superherotest.ui.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.malenalbc.superherotest.R;
import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.data.model.Url;
import com.malenalbc.superherotest.ui.master.ComicListActivity;
import com.squareup.picasso.Picasso;

import junit.framework.Assert;

/**
 * A fragment representing a single Comic detail screen. This fragment is either contained in a
 * {@link ComicListActivity} in two-pane mode (on tablets) or a {@link ComicDetailActivity} on
 * handsets.
 */
public class ComicDetailFragment extends Fragment implements ComicDetailContract.View {
    static final String ARG_COMIC = "argument.comic";

    private ComicDetailContract.Presenter presenter;

    private CollapsingToolbarLayout appBarLayout;
    private FloatingActionButton fabLink;
    private TextView txtDescription;

    static ComicDetailFragment newInstance () {
        return new ComicDetailFragment();
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
     * screen orientation changes).
     */
    public ComicDetailFragment () {
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume () {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause () {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comic_detail, container, false);

        appBarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.toolbar_layout);
        fabLink = (FloatingActionButton) rootView.findViewById(R.id.fab_link);
        txtDescription = ((TextView) rootView.findViewById(R.id.comic_detail));

        return rootView;
    }

    @Override
    public void setPresenter (ComicDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showComicDetail (@NonNull Comic comic, @Nullable String randomImageUrl) {
        Assert.assertNotNull("The comic to show cannot be null.", comic);

        appBarLayout.setTitle(comic.title);

        String description = comic.description;
        if (TextUtils.isEmpty(description)) {
            description = getString(R.string.no_description);
        }
        txtDescription.setText(description);

        fabLink.setOnClickListener(view -> {
            Url url = comic.urls.get(0);
            if (url != null) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.url));
                startActivity(browserIntent);
            }
        });

        if (!TextUtils.isEmpty(randomImageUrl)) {
            Picasso.with(getContext())
                    .load(randomImageUrl)
                    .into((ImageView) appBarLayout.findViewById(R.id.imgComic));
        }
    }
}
