package com.malenalbc.superherotest.ui.master;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.malenalbc.superherotest.R;
import com.malenalbc.superherotest.data.model.Character;
import com.malenalbc.superherotest.domain.GetComicsUseCase;
import com.malenalbc.superherotest.domain.GetSuperHeroUseCase;
import com.squareup.picasso.Picasso;

/**
 * Main activity representing a list of {@link com.malenalbc.superherotest.data.model.Comic}s
 * regarding a {@link Character}. Main toolbar functionality shows the character details, while a
 * {@link ComicListFragment} handles the list.
 */
public class ComicListActivity extends AppCompatActivity implements CharacterContract.View {
    private CharacterContract.Presenter presenter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ComicListFragment fragment =
                (ComicListFragment) getSupportFragmentManager().findFragmentById(
                        R.id.content_frame_layout);
        if (fragment == null) {
            // Create the fragment
            fragment = ComicListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame_layout, fragment)
                    .commit();
        }

        new ComicListPresenter(fragment, new GetComicsUseCase());

        presenter = new CharacterPresenter(this, new GetSuperHeroUseCase());
    }

    @Override
    protected void onResume () {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    protected void onStop () {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void showCharacter (Character character) {
        TextView description = (TextView) findViewById(R.id.txt_description);
        description.setText(character.getDescription());

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(getString(R.string.app_name));

            Picasso.with(this)
                    .load(character.getThumbnail().getUrl())
                    .into((ImageView) appBarLayout.findViewById(R.id.img_character));
        }
    }

    @Override
    public void setPresenter (CharacterContract.Presenter presenter) {
        // Nothing to do, the contractor and the view are the same
    }
}
