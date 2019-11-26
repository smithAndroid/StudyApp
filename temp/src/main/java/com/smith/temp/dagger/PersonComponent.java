package com.smith.temp.dagger;

import com.smith.temp.TempMainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PersonModule.class,FoodModule.class})
public interface PersonComponent {
    void inject(TempMainActivity activity);
}
