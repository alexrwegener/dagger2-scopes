package com.alexrwegener.dagger2byexamples.api.auth;

import com.alexrwegener.dagger2byexamples.data.user.User;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface AuthService {
    @POST("/auth") Observable<User> authenticate(@Body String username);
}
