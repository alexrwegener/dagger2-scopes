package com.alexrwegener.dagger2byexamples.android.preferences;

import android.content.SharedPreferences;

public final class IntPreference {
    private final SharedPreferences preferences;
    private final String key;
    private final int defaultValue;

    public IntPreference(SharedPreferences preferences, String key) {
        this(preferences, key, 0);
    }

    public IntPreference(SharedPreferences preferences, String key, int defaultValue) {
        this.preferences = preferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public int get() {
        return preferences.getInt(key, defaultValue);
    }

    public boolean isSet() {
        return preferences.contains(key);
    }

    public void set(int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public void delete() {
        preferences.edit().remove(key).apply();
    }

    public void reset() {
        preferences.edit().putInt(key, defaultValue).apply();
    }

    public boolean isDefault() {
        return get() == defaultValue;
    }

    public String key() {
        return key;
    }
}