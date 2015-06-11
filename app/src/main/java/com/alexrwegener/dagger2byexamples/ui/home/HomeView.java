package com.alexrwegener.dagger2byexamples.ui.home;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.alexrwegener.dagger2byexamples.R;
import com.alexrwegener.dagger2byexamples.data.user.User;
import com.alexrwegener.dagger2byexamples.di.DaggerService;
import com.alexrwegener.dagger2byexamples.di.user.UserComponent;
import javax.inject.Inject;

public class HomeView extends LinearLayout {
    @Inject HomePresenter presenter;
    @InjectView(R.id.home_first_name) TextView firstName;
    @InjectView(R.id.home_last_name) TextView lastName;
    @InjectView(R.id.home_enjoys_scopes) Switch enjoysScopesSwitch;

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        UserComponent userComponent = DaggerService.getUserComponent(context);
        HomeComponent homeComponent = DaggerHomeComponent.builder().userComponent(userComponent).build();
        homeComponent.inject(this);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override protected void onDetachedFromWindow() {
        presenter.dropView();
        super.onDetachedFromWindow();
    }

    void loadUser(User user, boolean enjoysScopes) {
        firstName.setText(user.firstName);
        lastName.setText(user.lastName);
        enjoysScopesSwitch.setChecked(enjoysScopes);
    }

    @OnClick(R.id.home_logout_button) void logout() {
        presenter.logout();
    }

    @OnCheckedChanged(R.id.home_enjoys_scopes) void enjoysScopes(Switch enjoysScopesSwitch) {
        presenter.isEnjoyingScopes(enjoysScopesSwitch.isChecked());
    }
}
