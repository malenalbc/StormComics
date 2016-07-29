package com.malenalbc.superherotest.ui.detail;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.ui.BasePresenter;
import com.malenalbc.superherotest.ui.BaseView;

public interface ComicDetailContract {
    interface View extends BaseView<Presenter> {
        void showComicDetail (@NonNull Comic comic, @Nullable String randomImageUrl);
    }

    interface Presenter extends BasePresenter {

    }
}
