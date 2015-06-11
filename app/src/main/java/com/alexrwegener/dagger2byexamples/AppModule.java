package com.alexrwegener.dagger2byexamples;

import android.app.Application;
import com.alexrwegener.dagger2byexamples.api.ApiModule;
import com.alexrwegener.dagger2byexamples.data.DataModule;
import com.alexrwegener.dagger2byexamples.di.ApplicationScope;
import com.alexrwegener.dagger2byexamples.interactor.InteractorModule;
import com.alexrwegener.dagger2byexamples.store.StoreModule;
import dagger.Module;
import dagger.Provides;

@Module(includes = { DataModule.class, ApiModule.class, StoreModule.class, InteractorModule.class }) public final class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides @ApplicationScope Application provideApplication() {
        return app;
    }
}
