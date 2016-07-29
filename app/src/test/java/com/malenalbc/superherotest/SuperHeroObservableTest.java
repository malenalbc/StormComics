package com.malenalbc.superherotest;

import com.malenalbc.superherotest.data.model.Character;
import com.malenalbc.superherotest.data.source.SuperHeroDataSource;
import com.malenalbc.superherotest.data.source.remote.SuperHeroRemoteDataSource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

public class SuperHeroObservableTest {
    private static final String NAME = "Stormy";
    SuperHeroDataSource dataSource;

    @Before
    public void before () {
        dataSource = Mockito.mock(SuperHeroRemoteDataSource.class);

        Character character = Mockito.mock(Character.class);
        when(character.getName()).thenReturn(NAME);
        when(dataSource.getSuperHero()).thenReturn(Observable.just(character));
    }

    @Test
    public void testGetSuperHero () {
        TestSubscriber<Character> testSubscriber = new TestSubscriber<>();
        dataSource.getSuperHero()
                .subscribe(testSubscriber);
        testSubscriber.assertNoErrors();

        Character character = testSubscriber.getOnNextEvents().get(0);
        Assert.assertTrue(character.getName() + " is supposed to be " + NAME,
                character.getName().equals(NAME));
    }
}
