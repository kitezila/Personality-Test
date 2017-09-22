package com.marchuk.affinitas.personalitytest.data.source;

import com.marchuk.affinitas.personalitytest.screens.QuestionActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component for data source injection
 * <p>
 * Created by Ievgen on 23.09.2017.
 */
@Singleton
@Component(modules = DataSourceModule.class)
public interface DataSourceComponent {

    void inject(QuestionActivity a);

}
