package com.alexrwegener.dagger2byexamples.android.preferences;

import android.content.SharedPreferences;

public final class StringPreference {
    private final SharedPreferences preferences;
    private final String key;
    private final String defaultValue;

    public StringPreference(SharedPreferences preferences, String key) {
        this(preferences, key, null);
    }

    public StringPreference(SharedPreferences preferences, String key, String defaultValue) {
        this.preferences = preferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String get() {
        return preferences.getString(key, defaultValue);
    }

    public boolean isSet() {
        return preferences.contains(key);
    }

    public void set(String value) {
        preferences.edit().putString(key, value).apply();
    }

    public void delete() {
        preferences.edit().remove(key).apply();
    }

    public void reset() {
        preferences.edit().putString(key, defaultValue).apply();
    }

    public boolean isDefault() {
        return get() == null && defaultValue == null || get().equals(defaultValue);
    }

    public String key() {
        return key;
    }
}