package com.malenalbc.superherotest.data.source;


import com.malenalbc.superherotest.data.model.Character;

import rx.Observable;

public interface SuperHeroDataSource {
    /**
     * Since we are working with just one superhero, we pass the id directly.
     */
    Observable<Character> getSuperHero();
}
