package com.alexrwegener.dagger2byexamples.ui.login;

import com.alexrwegener.dagger2byexamples.di.activity.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module public final class LoginModule {
    private final LoginPresenter loginPresenter;

    LoginModule(LoginPresenter presenter) {
        this.loginPresenter = presenter;
    }

    @ActivityScope @Provides LoginPresenter provideLoginPresenter() {
        return loginPresenter;
    }
}
