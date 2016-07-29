package com.malenalbc.superherotest.ui.master;


import com.malenalbc.superherotest.domain.GetSuperHeroUseCase;

import junit.framework.Assert;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class CharacterPresenter implements CharacterContract.Presenter {
    private final CharacterContract.View viewContract;
    private final CompositeSubscription subscriptions;

    private final GetSuperHeroUseCase superHeroUseCase;

    CharacterPresenter (
            CharacterContract.View viewContract,
            GetSuperHeroUseCase superHeroUseCase) {
        Assert.assertNotNull("The contract view cannot be null", viewContract);
        Assert.assertNotNull("The character use case cannot be null", superHeroUseCase);

        this.viewContract = viewContract;
        this.viewContract.setPresenter(this);
        this.superHeroUseCase = superHeroUseCase;
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe () {
        loadData();
    }

    private void loadData () {
        subscriptions.clear();
        Subscription subscription = superHeroUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewContract::showCharacter);
        subscriptions.add(subscription);
    }

    @Override
    public void unsubscribe () {
        subscriptions.clear();
    }
}
