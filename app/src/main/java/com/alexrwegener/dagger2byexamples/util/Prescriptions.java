package com.alexrwegener.dagger2byexamples.util;

import rx.Subscription;

public final class Prescriptions {
    private Prescriptions() {
        throw new AssertionError("No instances");
    }

    public static void safeUnsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

