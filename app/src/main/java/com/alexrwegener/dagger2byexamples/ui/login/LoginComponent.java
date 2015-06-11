package com.alexrwegener.dagger2byexamples.ui.login;

import com.alexrwegener.dagger2byexamples.di.ScreenScope;
import com.alexrwegener.dagger2byexamples.di.activity.ActivityComponent;
import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import dagger.Component;

@ScreenScope(LoginComponent.class) @Component(dependencies = ActivityComponent.class) public interface LoginComponent {
    void inject(LoginView view);

    NavigationOwner navigationOwner();
}
