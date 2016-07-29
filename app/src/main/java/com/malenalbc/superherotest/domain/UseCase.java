package com.malenalbc.superherotest.domain;


import rx.Observable;

interface UseCase<T extends Object> {
    Observable<T> execute(Object... args);
}
