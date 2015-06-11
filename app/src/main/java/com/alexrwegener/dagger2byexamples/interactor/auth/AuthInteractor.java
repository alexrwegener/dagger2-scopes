package com.alexrwegener.dagger2byexamples.interactor.auth;

import com.alexrwegener.dagger2byexamples.data.user.User;
import rx.Observable;

public interface AuthInteractor {
    Observable<Boolean> authenticate(String username);

    boolean isLoggedIn();

    String credentials();

    Observable<User> observeUserState();

    Observable<Boolean> logout();
}
