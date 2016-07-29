package com.malenalbc.superherotest.ui.master;


import android.support.annotation.NonNull;

import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.domain.GetComicsUseCase;

import junit.framework.Assert;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

class ComicListPresenter implements ComicListContract.Presenter {
    private final ComicListContract.View viewContract;
    private final CompositeSubscription subscriptions;

    private final GetComicsUseCase comicsUseCase;

    ComicListPresenter (
            @NonNull ComicListContract.View viewContract,
            @NonNull GetComicsUseCase comicsUseCase) {
        Assert.assertNotNull("The contract view cannot be null", viewContract);
        Assert.assertNotNull("The comic use case cannot be null", comicsUseCase);

        this.viewContract = viewContract;
        this.viewContract.setPresenter(this);
        this.comicsUseCase = comicsUseCase;
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void openComic (Comic comic) {
        viewContract.showComicDetail(comic);
    }

    @Override
    public void subscribe () {
        loadData();
    }

    private void loadData () {
        subscriptions.clear();
        Subscription subscription = comicsUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach(notification -> viewContract.hideLoading())
                .subscribe(
                        viewContract::showComics,
                        this::manageError
                );
        subscriptions.add(subscription);
    }

    private void manageError (Throwable e) {
        viewContract.showErrorMessage(e.getLocalizedMessage());
        e.printStackTrace();
    }

    @Override
    public void unsubscribe () {
        subscriptions.clear();
    }
}
