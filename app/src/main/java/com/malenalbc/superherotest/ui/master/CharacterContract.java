package com.malenalbc.superherotest.ui.master;


import com.malenalbc.superherotest.data.model.Character;
import com.malenalbc.superherotest.ui.BasePresenter;
import com.malenalbc.superherotest.ui.BaseView;

public interface CharacterContract {

    public interface View extends BaseView<Presenter>{
        void showCharacter (Character character);
    }

    public interface Presenter extends BasePresenter{
    }
}
