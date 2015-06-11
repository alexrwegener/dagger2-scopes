package com.alexrwegener.dagger2byexamples.ui.login;

import com.alexrwegener.dagger2byexamples.interactor.auth.AuthInteractor;
import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static com.alexrwegener.dagger2byexamples.util.Prescriptions.safeUnsubscribe;

public final class LoginPresenter {

    private final AuthInteractor authInteractor;
    private final NavigationOwner navigationOwner;
    private Subscription authSub;
    private LoginView loginView;

    @Inject LoginPresenter(AuthInteractor authInteractor, NavigationOwner navigationOwner) {
        this.authInteractor = authInteractor;
        this.navigationOwner = navigationOwner;
    }

    void takeView(LoginView view) {
        loginView = view;
    }

    void dropView() {
        safeUnsubscribe(authSub);
        loginView = null;
    }

    void login(String username) {
        authSub =
                authInteractor.authenticate(username).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
                    @Override public void call(Boolean isLoggedIn) {
                        //Handle auth failure
                        Timber.d("isLoggedIn? " + isLoggedIn);
                        if (loginView == null) {
                            return;
                        }
                        navigationOwner.showView(NavigationOwner.View.HOME);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Timber.e(throwable, "Error attempting to authenticate");
                    }
                });
    }
}
