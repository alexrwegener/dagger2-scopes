package com.alexrwegener.dagger2byexamples.api.auth;

import com.alexrwegener.dagger2byexamples.data.user.User;
import rx.Observable;

class MockAuthService implements AuthService {
    @Override public Observable<User> authenticate(String username) {
        if (username.equals("alex")) {
            return Observable.just(MockUsers.ALEX_WEGENER);
        }
        if (username.equals("jake")) {
            return Observable.just(MockUsers.JAKE_WHARTON);
        }
        if (username.equals("deer")) {
            return Observable.just(MockUsers.DEER);
        }
        return Observable.just(MockUsers.DEFAULT);
    }
}
