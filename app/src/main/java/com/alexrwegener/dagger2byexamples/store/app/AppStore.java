package com.alexrwegener.dagger2byexamples.store.app;

public interface AppStore {
    boolean isLoggedIn();

    void saveCredentials(String username);

    String getCredentials();
}
