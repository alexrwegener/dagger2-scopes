package com.alexrwegener.dagger2byexamples.data;

import android.app.Application;
import android.content.SharedPreferences;
import com.alexrwegener.dagger2byexamples.di.ApplicationScope;
import com.google.gson.Gson;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

import static android.content.Context.MODE_PRIVATE;
import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;
import static java.util.concurrent.TimeUnit.SECONDS;

@Module public final class DataModule {
    private static final String SHARED_PREFERENCES_NAME = "dagger2_by_examples";
    private static final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);

    @Provides @ApplicationScope SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
    }

    @Provides @ApplicationScope Gson provideGson() {
        return new Gson();
    }

    @Provides @ApplicationScope Converter provideGsonConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides @ApplicationScope OkHttpClient createOkHttpClient(Application application) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, SECONDS);
        client.setReadTimeout(10, SECONDS);
        client.setWriteTimeout(10, SECONDS);

        File cacheDir = new File(application.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        client.setCache(cache);

        return client;
    }

    @Provides @ApplicationScope Client provideOkClient() {
        return new OkClient(new OkHttpClient());
    }
}
