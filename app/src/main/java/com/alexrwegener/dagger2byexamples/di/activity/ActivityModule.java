package com.alexrwegener.dagger2byexamples.di.activity;

import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwnerModule;
import dagger.Module;

@Module(includes = NavigationOwnerModule.class) public final class ActivityModule {
}
