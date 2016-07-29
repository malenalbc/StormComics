package com.malenalbc.superherotest.ui.master;


import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.ui.BasePresenter;
import com.malenalbc.superherotest.ui.BaseView;

import java.util.List;

public interface ComicListContract {
    interface View extends BaseView<Presenter> {
        void hideLoading ();

        void showErrorMessage (String message);

        void showComics (List<Comic> comics);

        void showComicDetail (Comic comic);
    }

    interface Presenter extends BasePresenter {

        void openComic (Comic comic);
    }
}
