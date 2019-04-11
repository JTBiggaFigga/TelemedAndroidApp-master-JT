package com.mwellness.mcare.telemed.storage.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.io.File;

/**
 * Created by dev01 on 12/30/17.
 */

@Database(entities = {EMSRecord.class}, version = 1, exportSchema = false)
public abstract class EMSSqliteDatabase extends RoomDatabase {

    public abstract EMSRecordDao dao2();

    private static volatile EMSSqliteDatabase db;

    public synchronized static EMSSqliteDatabase getInstance(Context context) {

        if(db == null) {
            String fpath = context.getFilesDir().getAbsolutePath() + File.separator + "ems.db";
            db = Room.databaseBuilder(context, EMSSqliteDatabase.class, fpath).build();
        }

        return db;
    }

    /*public void insertNewVitalRecord(final VitalsRecord vitalsRecord) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.dao().insertVital(vitalsRecord);
                return null;
            }
        }.execute();
    }*/
}
