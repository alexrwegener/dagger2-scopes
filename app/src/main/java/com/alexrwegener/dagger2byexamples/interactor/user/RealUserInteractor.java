package com.alexrwegener.dagger2byexamples.interactor.user;

import com.alexrwegener.dagger2byexamples.data.user.User;
import com.alexrwegener.dagger2byexamples.store.user.UserStore;

public class RealUserInteractor implements UserInteractor {

    private final UserStore userStore;

    RealUserInteractor(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override public User user() {
        return userStore.user();
    }

    @Override public boolean isEnjoyingScopes() {
        return userStore.isEnjoyingScopes();
    }

    @Override public void isEnjoyingScopes(boolean isEnjoying) {
        userStore.isEnjoyingScopes(isEnjoying);
    }
}
