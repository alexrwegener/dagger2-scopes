package com.alexrwegener.dagger2byexamples.api.auth;

import com.alexrwegener.dagger2byexamples.data.user.User;

final class MockUsers {
    static final User JAKE_WHARTON = new User("Jake", "Wharton");
    static final User ALEX_WEGENER = new User("Alex", "Wegener");
    static final User DEER = new User("A", "Deer");
    static final User DEFAULT = new User("Default", "User");
}
