package com.malenalbc.superherotest.domain;


import com.malenalbc.superherotest.data.model.Character;
import com.malenalbc.superherotest.data.source.SuperHeroDataSource;
import com.malenalbc.superherotest.data.source.remote.SuperHeroRemoteDataSource;

import rx.Observable;

public class GetSuperHeroUseCase implements UseCase<Character> {
    private SuperHeroDataSource dataSource;

    public GetSuperHeroUseCase () {

    }

    @Override
    public Observable<Character> execute (Object... args) {
        return new SuperHeroRemoteDataSource().getSuperHero();
    }
}
