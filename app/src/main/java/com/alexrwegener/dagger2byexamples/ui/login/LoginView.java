package com.alexrwegener.dagger2byexamples.ui.login;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.alexrwegener.dagger2byexamples.R;
import com.alexrwegener.dagger2byexamples.di.DaggerService;
import com.alexrwegener.dagger2byexamples.di.activity.ActivityComponent;
import javax.inject.Inject;

public class LoginView extends LinearLayout {
    @Inject LoginPresenter presenter;
    @InjectView(R.id.login_username) EditText emailInput;
    @InjectView(R.id.login_button) Button loginButton;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ActivityComponent activityComponent = DaggerService.getActivityComponent(context);
        LoginComponent loginComponent = DaggerLoginComponent.builder().activityComponent(activityComponent).build();
        loginComponent.inject(this);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        emailInput.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this, 0);
        presenter.takeView(this);
    }

    @Override protected void onDetachedFromWindow() {
        presenter.dropView();
        super.onDetachedFromWindow();
    }

    @OnClick(R.id.login_button) void onSignIn() {
        presenter.login(getUsername());
    }

    private String getUsername() {
        return emailInput.getText().toString();
    }
}
