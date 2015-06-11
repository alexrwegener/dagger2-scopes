package com.alexrwegener.dagger2byexamples.di;

import android.content.Context;
import com.alexrwegener.dagger2byexamples.AppComponent;
import com.alexrwegener.dagger2byexamples.di.activity.ActivityComponent;
import com.alexrwegener.dagger2byexamples.di.user.UserComponent;
import java.lang.reflect.Method;

public final class DaggerService {
    private static final String INJECTOR_SERVICE = "com.alexrwegener.dagger2byexamples.injector";
    private static final String USER_INJECTOR_SERVICE = "com.alexrwegener.dagger2byexamples.injector.user";

    private DaggerService() {
        throw new AssertionError("No instances");
    }

    @SuppressWarnings("ResourceType") // Explicitly doing a custom service.
    public static AppComponent getAppComponent(Context applicationContext) {
        return (AppComponent) applicationContext.getSystemService(INJECTOR_SERVICE);
    }

    @SuppressWarnings("ResourceType") // Explicitly doing a custom service.
    public static ActivityComponent getActivityComponent(Context activityContext) {
        return (ActivityComponent) activityContext.getSystemService(INJECTOR_SERVICE);
    }

    @SuppressWarnings("ResourceType") // Explicitly doing a custom service.
    public static UserComponent getUserComponent(Context activityContext) {
        return (UserComponent) activityContext.getSystemService(USER_INJECTOR_SERVICE);
    }

    public static boolean matchesUserInjectorService(String name) {
        return USER_INJECTOR_SERVICE.equals(name);
    }

    public static boolean matchesInjectorService(String name) {
        return INJECTOR_SERVICE.equals(name);
    }

    //Unused. Example of how to use reflection to bypass SomeComponent.builder().blah(new Blah("hi")).build()
    public static <T> T createComponent(Class<T> componentClass, Object... dependencies) {
        String fqn = componentClass.getName();
        String packageName = componentClass.getPackage().getName();
        // Accounts for inner classes, ie MyApplication$Component
        String simpleName = fqn.substring(packageName.length() + 1);
        String generatedName = (packageName + ".Dagger" + simpleName).replace('$', '_');
        try {
            Class<?> generatedClass = Class.forName(generatedName);
            Object builder = generatedClass.getMethod("builder").invoke(null);
            for (Method method : builder.getClass().getMethods()) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1) {
                    Class<?> dependencyClass = params[0];
                    for (Object dependency : dependencies) {
                        if (dependencyClass.isAssignableFrom(dependency.getClass())) {
                            method.invoke(builder, dependency);
                            break;
                        }
                    }
                }
            }
            //noinspection unchecked
            return (T) builder.getClass().getMethod("build").invoke(builder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
