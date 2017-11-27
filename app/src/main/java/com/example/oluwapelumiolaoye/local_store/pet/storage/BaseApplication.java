package com.example.oluwapelumiolaoye.local_store.pet.storage;

import android.app.Application;

import com.example.oluwapelumiolaoye.local_store.pet.activity.MainActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by oluwapelumi.olaoye on 11/25/17.
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .migration(new MigrationR())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
