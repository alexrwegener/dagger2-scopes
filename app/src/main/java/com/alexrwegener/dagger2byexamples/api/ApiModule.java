package com.alexrwegener.dagger2byexamples.api;

import com.alexrwegener.dagger2byexamples.api.auth.AuthModule;
import dagger.Module;

@Module(includes = { AuthModule.class }) public final class ApiModule {
}
