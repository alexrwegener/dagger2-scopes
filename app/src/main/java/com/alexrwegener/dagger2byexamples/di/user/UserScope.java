package com.alexrwegener.dagger2byexamples.di.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

@Scope @Retention(RetentionPolicy.RUNTIME) public @interface UserScope {
}