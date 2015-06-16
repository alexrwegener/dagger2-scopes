package com.alexrwegener.dagger2byexamples.di.user;

import com.alexrwegener.dagger2byexamples.di.activity.ActivityComponent;
import com.alexrwegener.dagger2byexamples.interactor.user.UserInteractor;
import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import dagger.Component;

@UserScope @Component(dependencies = ActivityComponent.class, modules = UserModule.class) public interface UserComponent {

    NavigationOwner navigationOwner();

    UserInteractor userInteractor();
}