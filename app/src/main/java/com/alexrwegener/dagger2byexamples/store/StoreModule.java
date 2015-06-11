package com.alexrwegener.dagger2byexamples.store;

import com.alexrwegener.dagger2byexamples.store.app.AppStoreModule;
import dagger.Module;

@Module(includes = { AppStoreModule.class }) public final class StoreModule {
}
