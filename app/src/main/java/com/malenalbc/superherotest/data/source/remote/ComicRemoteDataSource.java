package com.malenalbc.superherotest.data.source.remote;


import android.support.annotation.Nullable;

import com.malenalbc.superherotest.data.api.Client;
import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.data.source.ComicDataSource;

import java.util.List;

import rx.Observable;

public class ComicRemoteDataSource implements ComicDataSource {
    private Client.ClientService service;

    public ComicRemoteDataSource () {
        service = Client.createService(Client.ClientService.class);
    }


    @Override
    public Observable<List<Comic>> getComics (@Nullable String dateRange) {
        return service.getComics(dateRange)
                .map(wrapper -> wrapper.getData().getResults());
    }
}
