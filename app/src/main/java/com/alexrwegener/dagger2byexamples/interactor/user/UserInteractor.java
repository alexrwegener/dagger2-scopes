package com.alexrwegener.dagger2byexamples.interactor.user;

import com.alexrwegener.dagger2byexamples.data.user.User;

public interface UserInteractor {
    User user();

    boolean isEnjoyingScopes();

    void isEnjoyingScopes(boolean isEnjoying);
}
