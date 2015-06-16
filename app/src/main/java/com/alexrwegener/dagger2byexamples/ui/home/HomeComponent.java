package com.alexrwegener.dagger2byexamples.ui.home;

import com.alexrwegener.dagger2byexamples.di.ScreenScope;
import com.alexrwegener.dagger2byexamples.di.user.UserComponent;
import com.alexrwegener.dagger2byexamples.interactor.user.UserInteractor;
import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import dagger.Component;

@ScreenScope(HomeComponent.class) @Component(dependencies = UserComponent.class) interface HomeComponent {
    void inject(HomeView view);

    NavigationOwner owner();

    UserInteractor userInteractor();
}
