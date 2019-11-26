package com.smith.temp.dagger;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonModule {


    @Singleton
    @Named("a")
    @Provides
    public Person providePersion() {
        return new Person("Tom",29);
    }

    @Named("b")
    @Provides
    public Person providePersionb() {
        return new Person();
    }

    @Named("c")
    @Provides
    public Person providePersionc(Food food) {
        return new Person("smith",31,food);
    }


}
