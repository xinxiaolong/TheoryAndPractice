package com.example.fragment.theoryandpractice.daggerPractice;

import com.example.fragment.theoryandpractice.MainActivity;

import dagger.Component;

/**
 * Created by xiaolong on 2017/1/10.
 */


@Component(modules = {DrinkModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
