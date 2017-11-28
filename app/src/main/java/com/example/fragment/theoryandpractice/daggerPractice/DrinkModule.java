package com.example.fragment.theoryandpractice.daggerPractice;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaolong on 2017/1/10.
 */

@Module
public class DrinkModule {

    Context context;
    public DrinkModule(Context context){
        this.context=context;
    }

    @Provides
    public Apple provideApple() {
        return new Apple();
    }

    @Provides
    public AppleDrink provideAppleDrink(Apple apple) {
        return new AppleDrink(apple);
    }

    @Provides
    public Bottle provideBottle(AppleDrink drink){
        Bottle bottle=new Bottle();
        bottle.setDrink(drink);
        return bottle;
    }

}
