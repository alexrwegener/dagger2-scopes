package com.alexrwegener.dagger2byexamples.interactor.auth;

import com.alexrwegener.dagger2byexamples.api.auth.AuthService;
import com.alexrwegener.dagger2byexamples.di.ApplicationScope;
import com.alexrwegener.dagger2byexamples.store.app.AppStore;
import dagger.Module;
import dagger.Provides;

@Module public final class AuthInteractorModule {
    @Provides @ApplicationScope AuthInteractor provideAuthInteractor(AuthService authService, AppStore appStore) {
        return new RealAuthInteractor(authService, appStore);
    }
}
