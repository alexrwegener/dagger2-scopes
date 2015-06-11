package com.alexrwegener.dagger2byexamples.ui.home;

import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import com.alexrwegener.dagger2byexamples.store.user.UserStore;
import javax.inject.Inject;

public final class HomePresenter {

    private final UserStore userStore;
    private final NavigationOwner navigationOwner;
    private HomeView view;

    @Inject HomePresenter(UserStore userStore, NavigationOwner navigationOwner) {
        this.userStore = userStore;
        this.navigationOwner = navigationOwner;
    }

    void takeView(HomeView view) {
        this.view = view;
        view.loadUser(userStore.user(), userStore.isEnjoyingScopes());
    }

    void dropView() {
        view = null;
    }

    void isEnjoyingScopes(boolean isEnjoying) {
        userStore.isEnjoyingScopes(isEnjoying);
    }

    public void logout() {
        navigationOwner.showView(NavigationOwner.View.LOGIN);
    }
}
