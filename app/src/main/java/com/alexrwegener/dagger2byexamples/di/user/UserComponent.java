package com.alexrwegener.dagger2byexamples.di.user;

import com.alexrwegener.dagger2byexamples.di.activity.ActivityComponent;
import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import com.alexrwegener.dagger2byexamples.store.user.UserStore;
import com.alexrwegener.dagger2byexamples.ui.home.HomeView;
import dagger.Component;

@UserScope @Component(dependencies = ActivityComponent.class, modules = UserModule.class) public interface UserComponent {

    void inject(HomeView homeView);

    NavigationOwner navigationOwner();

    UserStore userStore();
}