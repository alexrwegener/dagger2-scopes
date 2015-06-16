package com.alexrwegener.dagger2byexamples.ui.home;

import com.alexrwegener.dagger2byexamples.interactor.user.UserInteractor;
import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import javax.inject.Inject;

public final class HomePresenter {

    private final UserInteractor userInteractor;
    private final NavigationOwner navigationOwner;
    private HomeView view;

    @Inject HomePresenter(UserInteractor userInteractor, NavigationOwner navigationOwner) {
        this.userInteractor = userInteractor;
        this.navigationOwner = navigationOwner;
    }

    void takeView(HomeView view) {
        this.view = view;
        view.loadUser(userInteractor.user(), userInteractor.isEnjoyingScopes());
    }

    void dropView() {
        view = null;
    }

    void isEnjoyingScopes(boolean isEnjoying) {
        userInteractor.isEnjoyingScopes(isEnjoying);
    }

    public void logout() {
        navigationOwner.showView(NavigationOwner.View.LOGIN);
    }
}
