package com.smith.temp.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class FoodModule {

    @Provides
    public Food provideFood() {
        return new Food();
    }
}
