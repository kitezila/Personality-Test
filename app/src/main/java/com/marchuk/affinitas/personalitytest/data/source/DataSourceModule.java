package com.marchuk.affinitas.personalitytest.data.source;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Define application data source container
 * <p>
 * Created by Ievgen on 23.09.2017.
 */
@Module
class DataSourceModule {
    @Provides
    @NonNull
    @Singleton
    QuestionDataSource getDataSource() {
        return new QuestionDataStub();
    }
}
