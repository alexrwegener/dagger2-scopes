package com.alexrwegener.dagger2byexamples.owner.navigation;

import com.alexrwegener.dagger2byexamples.R;
import timber.log.Timber;

public final class NavigationOwner {

    private Activity activity;

    public void takeActivity(Activity activity) {
        this.activity = activity;
    }

    public void dropActivity() {
        activity = null;
    }

    public void showView(View view) {
        if (activity != null) {
            activity.showView(view);
        } else {
            Timber.e(Activity.class.getSimpleName() + " is null");
        }
    }

    public enum View {
        LOGIN(R.id.login_button), HOME(R.layout.home_view);

        private int layoutId;

        View(int layoutId) {
            this.layoutId = layoutId;
        }
    }

    public interface Activity {
        void showView(View view);
    }
}
