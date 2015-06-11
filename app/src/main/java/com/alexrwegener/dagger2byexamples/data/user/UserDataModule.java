package com.alexrwegener.dagger2byexamples.data.user;

import android.app.Application;
import android.content.SharedPreferences;
import com.alexrwegener.dagger2byexamples.di.user.UserScope;
import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

@Module public final class UserDataModule {
    private static final String SHARED_PREFERENCES_NAME = "dagger2_by_examples.";
    private final String username;
    private final User user;

    public UserDataModule(String username, User user) {
        this.username = username;
        this.user = user;
    }

    //See if you need to provide a qualifier here even though they have different scopes
    @Provides @UserScope SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences(SHARED_PREFERENCES_NAME + username, MODE_PRIVATE);
    }

    @Provides @UserScope User provideUser() {
        return user;
    }
}
