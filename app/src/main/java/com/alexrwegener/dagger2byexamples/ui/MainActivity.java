package com.alexrwegener.dagger2byexamples.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.alexrwegener.dagger2byexamples.AppComponent;
import com.alexrwegener.dagger2byexamples.R;
import com.alexrwegener.dagger2byexamples.data.user.User;
import com.alexrwegener.dagger2byexamples.data.user.UserDataModule;
import com.alexrwegener.dagger2byexamples.di.DaggerService;
import com.alexrwegener.dagger2byexamples.di.activity.ActivityComponent;
import com.alexrwegener.dagger2byexamples.di.activity.DaggerActivityComponent;
import com.alexrwegener.dagger2byexamples.di.user.DaggerUserComponent;
import com.alexrwegener.dagger2byexamples.di.user.UserComponent;
import com.alexrwegener.dagger2byexamples.interactor.auth.AuthInteractor;
import com.alexrwegener.dagger2byexamples.owner.navigation.NavigationOwner;
import javax.inject.Inject;
import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

import static com.alexrwegener.dagger2byexamples.util.Prescriptions.safeUnsubscribe;

public class MainActivity extends ActionBarActivity implements NavigationOwner.Activity {

    @Inject AuthInteractor authInteractor;
    @Inject NavigationOwner navigationOwner;
    @InjectView(R.id.container) ViewGroup container;
    private ActivityComponent activityComponent;
    private UserComponent userComponent;
    private Subscription authSub;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppComponent appComponent = DaggerService.getAppComponent(getApplication());
        activityComponent = DaggerActivityComponent.builder().appComponent(appComponent).build();
        activityComponent.inject(this);

        appComponent.getActivitySubComponent();

        ButterKnife.inject(this);
        navigationOwner.takeActivity(this);
        authSub = authInteractor.observeUserState().subscribe(new Action1<User>() {
            @Override public void call(User user) {
                if (user != null) {
                    userComponent = DaggerUserComponent.builder()
                            .activityComponent(activityComponent)
                            .userDataModule(new UserDataModule(authInteractor.credentials(), user))
                            .build();
                } else {
                    userComponent = null;
                }
            }
        });
        if (authInteractor.isLoggedIn()) {
            showView(NavigationOwner.View.HOME);
        } else {
            showView(NavigationOwner.View.LOGIN);
        }
    }

    @Override public Object getSystemService(@NonNull String name) {
        if (DaggerService.matchesUserInjectorService(name)) {
            return userComponent;
        }
        if (DaggerService.matchesInjectorService(name)) {
            return activityComponent;
        }
        return super.getSystemService(name);
    }

    @Override public void showView(NavigationOwner.View view) {
        Timber.d("To " + view);
        container.removeAllViews();
        switch (view) {
            case HOME:
                getLayoutInflater().inflate(R.layout.home_view, container);
                return;
            case LOGIN:
                getLayoutInflater().inflate(R.layout.login_view, container);
                return;
            default:
                throw new IllegalStateException("Define this view: " + view + " within " + MainActivity.class.getSimpleName());
        }
    }

    @Override protected void onDestroy() {
        safeUnsubscribe(authSub);
        navigationOwner.dropActivity();
        super.onDestroy();
    }
}
