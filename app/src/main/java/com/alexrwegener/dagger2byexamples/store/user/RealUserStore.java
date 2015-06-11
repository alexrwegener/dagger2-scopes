package com.alexrwegener.dagger2byexamples.store.user;

import android.content.SharedPreferences;
import com.alexrwegener.dagger2byexamples.android.preferences.BooleanPreference;
import com.alexrwegener.dagger2byexamples.android.preferences.StringPreference;
import com.alexrwegener.dagger2byexamples.data.user.User;

final class RealUserStore implements UserStore {
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String ENJOYS_SCOPES = "enjoys_scopes";
    private final StringPreference firstName;
    private final StringPreference lastName;
    private final BooleanPreference enjoysScopes;

    RealUserStore(SharedPreferences sharedPreferences, User user) {
        this.firstName = new StringPreference(sharedPreferences, FIRST_NAME);
        this.lastName = new StringPreference(sharedPreferences, LAST_NAME);
        this.enjoysScopes = new BooleanPreference(sharedPreferences, ENJOYS_SCOPES);
        this.firstName.set(user.firstName);
        this.lastName.set(user.lastName);
    }

    @Override public User user() {
        return new User(firstName.get(), lastName.get());
    }

    @Override public boolean isEnjoyingScopes() {
        return enjoysScopes.get();
    }

    @Override public void isEnjoyingScopes(boolean isEnjoying) {
        enjoysScopes.set(isEnjoying);
    }
}