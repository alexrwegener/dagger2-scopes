package com.alexrwegener.dagger2byexamples.store.user;

import com.alexrwegener.dagger2byexamples.data.user.User;

public interface UserStore {
    User user();

    boolean isEnjoyingScopes();

    void isEnjoyingScopes(boolean isEnjoying);
}