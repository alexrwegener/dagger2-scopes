package com.alexrwegener.dagger2byexamples.store.user;

import android.content.SharedPreferences;
import com.alexrwegener.dagger2byexamples.data.user.User;
import com.alexrwegener.dagger2byexamples.di.user.UserScope;
import dagger.Module;
import dagger.Provides;

@Module public final class UserStoreModule {

    @UserScope @Provides UserStore provideUserStore(SharedPreferences sharedPreferences, User user) {
        return new RealUserStore(sharedPreferences, user);
    }
}
