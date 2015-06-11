package com.alexrwegener.dagger2byexamples.store.app;

import static com.alexrwegener.dagger2byexamples.util.Strings.isBlank;

final class RealAppStore implements AppStore {
    private String username;

    @Override public boolean isLoggedIn() {
        return !isBlank(username);
    }

    @Override public void saveCredentials(String username) {
        this.username = username;
    }

    @Override public String getCredentials() {
        return username;
    }
}
