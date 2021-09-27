package com.demo.mobile.app.di.component;


import com.demo.mobile.app.di.module.ApiModule;
import com.demo.mobile.app.di.module.AppModule;
import com.demo.mobile.app.di.module.LocalModule;
import com.demo.mobile.app.di.module.ManagerModule;
import com.demo.mobile.app.di.module.NetworkModule;
import com.demo.mobile.app.di.module.RepositoryModule;
import com.demo.mobile.app.di.module.SystemModule;
import com.demo.mobile.app.di.module.ViewModelModule;
import com.demo.mobile.app.di.module.androidcomponent.AndroidComponentsModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        LocalModule.class,
        ManagerModule.class,
        ApiModule.class,
        RepositoryModule.class,
        NetworkModule.class,
        SystemModule.class,
        AndroidComponentsModule.class,
        ViewModelModule.class,


})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<DaggerApplication> {

    }


}
