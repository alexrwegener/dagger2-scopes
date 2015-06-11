package com.alexrwegener.dagger2byexamples.interactor;

import com.alexrwegener.dagger2byexamples.interactor.auth.AuthInteractorModule;
import dagger.Module;

@Module(includes = AuthInteractorModule.class) public final class InteractorModule {
}
