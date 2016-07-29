package com.malenalbc.superherotest.ui.detail;


import android.support.annotation.NonNull;

import com.malenalbc.superherotest.data.model.Comic;

import junit.framework.Assert;

import java.util.Random;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class ComicDetailPresenter implements ComicDetailContract.Presenter {
    private final ComicDetailContract.View viewContract;
    private final Comic comic;

    ComicDetailPresenter (@NonNull ComicDetailContract.View viewContract,
                          @NonNull Comic comic) {
        Assert.assertNotNull("The contract view cannot be null", viewContract);
        this.viewContract = viewContract;
        this.viewContract.setPresenter(this);
        this.comic = comic;
    }

    @Override
    public void subscribe () {
        Observable.OnSubscribe<String> observable = subscriber -> {
            subscriber.onNext(getRandomImageUrl());
            subscriber.onCompleted();
        };
        Observable.create(observable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(url -> viewContract.showComicDetail(comic, url));
    }

    private String getRandomImageUrl () {
        int size = comic.images.size();
        if (size > 0) {
            int randomNum = new Random().nextInt(size);
            return comic.images.get(randomNum).getUrl();
        } else {
            return "";
        }
    }

    @Override
    public void unsubscribe () {
        // Nothing to do
    }
}
