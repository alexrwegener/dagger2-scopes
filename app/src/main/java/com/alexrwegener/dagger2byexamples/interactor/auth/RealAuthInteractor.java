package com.alexrwegener.dagger2byexamples.interactor.auth;

import com.alexrwegener.dagger2byexamples.api.auth.AuthService;
import com.alexrwegener.dagger2byexamples.data.user.User;
import com.alexrwegener.dagger2byexamples.store.app.AppStore;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

public class RealAuthInteractor implements AuthInteractor {

    private final BehaviorSubject<User> authSubject;
    private final AuthService authService;
    private final AppStore appStore;

    RealAuthInteractor(AuthService authService, AppStore appStore) {
        this.authService = authService;
        this.appStore = appStore;
        this.authSubject = BehaviorSubject.create();
    }

    public Observable<Boolean> authenticate(final String username) {
        return authService.authenticate(username).doOnNext(new Action1<User>() {
            @Override public void call(final User user) {
                appStore.saveCredentials(username);
                authSubject.onNext(user);
            }
        }).map(new Func1<User, Boolean>() {
            @Override public Boolean call(User s) {
                return s != null;
            }
        });
    }

    @Override public boolean isLoggedIn() {
        return appStore.isLoggedIn();
    }

    @Override public String credentials() {
        return appStore.getCredentials();
    }

    @Override public Observable<User> observeUserState() {
        return authSubject.asObservable().distinctUntilChanged();
    }

    @Override public Observable<Boolean> logout() {
        appStore.saveCredentials("");
        authSubject.onNext(null);
        return Observable.just(true);
    }
}
