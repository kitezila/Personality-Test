package com.marchuk.affinitas.personalitytest;

import android.app.Application;

import com.marchuk.affinitas.personalitytest.data.source.DaggerDataSourceComponent;
import com.marchuk.affinitas.personalitytest.data.source.DataSourceComponent;

/**
 * Initialization of Dagger components
 *
 * Created by Ievgen on 23.09.2017.
 */
public class App extends Application {
    private static DataSourceComponent mDataSourceComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataSourceComponent = DaggerDataSourceComponent.create();
    }

    public static DataSourceComponent getDataSourceComponent() {
        return mDataSourceComponent;
    }
}
