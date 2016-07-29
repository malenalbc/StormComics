package com.malenalbc.superherotest.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.malenalbc.superherotest.R;
import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.ui.master.ComicListActivity;

/**
 * An activity representing a single Comic detail screen. This activity is only used narrow width
 * devices. On tablet-size devices, item details are presented side-by-side with a list of items in
 * a {@link ComicListActivity}.
 */
public class ComicDetailActivity extends AppCompatActivity {

    public static Intent newIntent (Activity activity, Comic comic) {
        Intent intent = new Intent(activity, ComicDetailActivity.class);
        intent.putExtra(ComicDetailFragment.ARG_COMIC, comic);
        return intent;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        Comic comic = getIntent().getParcelableExtra(ComicDetailFragment.ARG_COMIC);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ComicDetailFragment fragment = (ComicDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.comic_detail_container);
        if (fragment == null) {
            fragment = addComicDetailFragment();
        }
        new ComicDetailPresenter(fragment, comic);
    }

    private ComicDetailFragment addComicDetailFragment () {
        ComicDetailFragment fragment = ComicDetailFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.comic_detail_container, fragment)
                .commit();
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            NavUtils.navigateUpTo(this, new Intent(this, ComicListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
