package com.malenalbc.superherotest.domain;


import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.data.source.remote.ComicRemoteDataSource;

import java.util.List;

import rx.Observable;

public class GetComicsUseCase implements UseCase<List<Comic>> {
    // Placeholder dates (Ones that we know give results). The proper approach would be
    // incremental searching from a starting date.
    private static final String INITIAL_DATE_FROM = "2012-01-01";
    private static final String INITIAL_DATE_TO = "2012-12-01";

    @Override
    public Observable<List<Comic>> execute (Object... args) {
//        String dateFrom = (String) args[0];
//        String dateTo = (String) args[0];
        // TODO Take a date and search for a span of time
        return new ComicRemoteDataSource().getComics(INITIAL_DATE_FROM + "," + INITIAL_DATE_TO);
    }
}
