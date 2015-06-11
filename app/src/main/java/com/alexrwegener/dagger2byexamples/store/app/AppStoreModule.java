package com.alexrwegener.dagger2byexamples.store.app;

import com.alexrwegener.dagger2byexamples.di.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module public final class AppStoreModule {
    @ApplicationScope @Provides AppStore provideAppStore() {
        return new RealAppStore();
    }
}
