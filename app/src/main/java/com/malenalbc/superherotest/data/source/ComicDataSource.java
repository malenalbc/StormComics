package com.malenalbc.superherotest.data.source;


import android.support.annotation.Nullable;

import com.malenalbc.superherotest.data.model.Comic;

import java.util.List;

import rx.Observable;

/**
 * For scalability and encapsulating purposes, the main data source it's an interface we later
 * implement how we need. In this simple example we only have one for api calls, but we could also
 * have a ComicLocalDataSource if we wanted to persist the data.
 */
public interface ComicDataSource {

    Observable<List<Comic>> getComics (@Nullable String dateRange);
}
