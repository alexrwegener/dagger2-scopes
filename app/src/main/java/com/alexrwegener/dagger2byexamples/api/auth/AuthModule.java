package com.alexrwegener.dagger2byexamples.api.auth;

import com.alexrwegener.dagger2byexamples.di.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.Converter;
import timber.log.Timber;

@Module public final class AuthModule {

    @Provides @ApplicationScope AuthService provideAuthRestAdapter(Client client, Converter converter) {
        MockRestAdapter mockRestAdapter = MockRestAdapter.from(
                new RestAdapter.Builder().setClient(client).setConverter(converter).setLogLevel(RestAdapter.LogLevel.BASIC).setLog(new RestAdapter.Log() {
                    @Override public void log(String message) {
                        Timber.d(message);
                    }
                }).setEndpoint("https://alexrwegener.com/").build());
        mockRestAdapter.setDelay(100);
        return mockRestAdapter.create(AuthService.class, new MockAuthService());
    }
}
