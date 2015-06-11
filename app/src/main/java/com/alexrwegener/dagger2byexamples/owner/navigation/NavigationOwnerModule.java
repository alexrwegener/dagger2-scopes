package com.alexrwegener.dagger2byexamples.owner.navigation;

import com.alexrwegener.dagger2byexamples.di.activity.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module public final class NavigationOwnerModule {
    @Provides @ActivityScope NavigationOwner provideUserOwner() {
        return new NavigationOwner();
    }
}
