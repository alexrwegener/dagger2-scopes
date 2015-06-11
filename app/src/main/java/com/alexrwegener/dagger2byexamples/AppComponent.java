package com.alexrwegener.dagger2byexamples;

import android.app.Application;
import com.alexrwegener.dagger2byexamples.di.ApplicationScope;
import com.alexrwegener.dagger2byexamples.interactor.auth.AuthInteractor;
import dagger.Component;

@ApplicationScope @Component(modules = AppModule.class) public interface AppComponent {
    Application application();

    AuthInteractor authInteractor();

    ActivitySubComponent getActivitySubComponent();
}
