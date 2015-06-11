package com.alexrwegener.dagger2byexamples.di.activity;

import android.app.Application;
import com.alexrwegener.dagger2byexamples.AppComponent;
import com.alexrwegener.dagger2byexamples.interactor.auth.AuthInteractor;
import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import com.alexrwegener.dagger2byexamples.ui.MainActivity;
import dagger.Component;

@ActivityScope @Component(dependencies = AppComponent.class, modules = ActivityModule.class) public interface ActivityComponent {

    void inject(MainActivity activity);

    AuthInteractor authInteractor();

    NavigationOwner navigationOwner();

    Application application();
}
