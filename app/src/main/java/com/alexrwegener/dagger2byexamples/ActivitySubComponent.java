package com.alexrwegener.dagger2byexamples;

import com.alexrwegener.dagger2byexamples.di.activity.ActivityModule;
import com.alexrwegener.dagger2byexamples.di.activity.ActivityScope;
import dagger.Subcomponent;

//Unused. Example of how to create a sub component
@Subcomponent(modules = ActivityModule.class) @ActivityScope public interface ActivitySubComponent {

}
