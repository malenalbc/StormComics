package com.malenalbc.superherotest;


import com.malenalbc.superherotest.data.model.Comic;
import com.malenalbc.superherotest.data.source.remote.ComicRemoteDataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

public class ComicDataObservableTest {
    private ComicRemoteDataSource dataSource;

    @Before
    public void before () {
        dataSource = Mockito.mock(ComicRemoteDataSource.class);

        Comic comic = Mockito.mock(Comic.class);
        List<Comic> comics = new ArrayList<>();
        comics.add(comic);
        comics.add(comic);
        comics.add(comic);
        when(dataSource.getComics(null)).thenReturn(Observable.just(comics));
    }

    @Test
    public void testGetComics (){
        TestSubscriber<List<Comic>> testSubscriber = new TestSubscriber<>();
        dataSource.getComics(null)
                .subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        List<Comic> comics = testSubscriber.getOnNextEvents().get(0);
        Assert.assertTrue(comics.size() > 0);
    }
}
