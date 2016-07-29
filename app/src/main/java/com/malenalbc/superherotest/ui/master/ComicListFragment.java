package com.malenalbc.superherotest.ui.master;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.malenalbc.superherotest.R;
import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.ui.detail.ComicDetailActivity;

import java.util.List;

public class ComicListFragment extends Fragment implements ComicListContract.View {

    private ComicListContract.Presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recList;

    public static ComicListFragment newInstance () {
        return new ComicListFragment();
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comic_list_view, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        recList = (RecyclerView) rootView.findViewById(R.id.cardList);

        return rootView;
    }

    @Override
    public void onResume () {
        super.onResume();
        presenter.subscribe();

    }

    @Override
    public void onStop () {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void hideLoading () {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage (String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComics (List<Comic> comics) {
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        // TODO Handle endless scrolling

        ComicListAdapter adapter = new ComicListAdapter(comics, presenter::openComic);
        recList.setAdapter(adapter);
    }

    @Override
    public void showComicDetail (Comic comic) {
        Intent intent = ComicDetailActivity.newIntent(getActivity(), comic);
        startActivity(intent);
    }

    @Override
    public void setPresenter (ComicListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
