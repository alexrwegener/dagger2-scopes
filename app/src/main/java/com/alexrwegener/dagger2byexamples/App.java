package com.alexrwegener.dagger2byexamples;

import android.app.Application;
import android.support.annotation.NonNull;
import com.alexrwegener.dagger2byexamples.di.DaggerService;
import timber.log.Timber;

public class App extends Application {
    private AppComponent component;

    @Override public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.d("Initializing App");
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @Override public Object getSystemService(@NonNull String name) {
        if (DaggerService.matchesInjectorService(name)) {
            return component;
        }
        return super.getSystemService(name);
    }
}
