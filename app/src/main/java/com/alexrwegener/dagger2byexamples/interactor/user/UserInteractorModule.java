package com.alexrwegener.dagger2byexamples.interactor.user;

import com.alexrwegener.dagger2byexamples.di.user.UserScope;
import com.alexrwegener.dagger2byexamples.store.user.UserStore;
import dagger.Module;
import dagger.Provides;

@Module public final class UserInteractorModule {
    @Provides @UserScope UserInteractor provideUserInteractor(UserStore userStore) {
        return new RealUserInteractor(userStore);
    }
}
