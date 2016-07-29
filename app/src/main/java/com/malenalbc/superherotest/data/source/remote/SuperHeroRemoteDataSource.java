package com.malenalbc.superherotest.data.source.remote;


import com.malenalbc.superherotest.data.api.Client;
import com.malenalbc.superherotest.data.model.Character;
import com.malenalbc.superherotest.data.source.SuperHeroDataSource;

import rx.Observable;

public class SuperHeroRemoteDataSource implements SuperHeroDataSource {

    private Client.ClientService service;

    public SuperHeroRemoteDataSource () {
        service = Client.createService(Client.ClientService.class);
    }


    @Override
    public Observable<Character> getSuperHero () {
        return service.getCharacter()
                .map(wrapper -> wrapper.getData().getResults().get(0));
    }
}
