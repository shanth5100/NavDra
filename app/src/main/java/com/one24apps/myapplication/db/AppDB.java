package com.one24apps.myapplication.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.one24apps.myapplication.dao.BillsDao;
import com.one24apps.myapplication.model.Bills;

@Database(entities = {Bills.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;

    public abstract BillsDao billsDao (); //abstract

    public synchronized static AppDB getInstance(Context context) {
        if (instance != null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDB.class, "app_db")
            .fallbackToDestructiveMigration() // with out this we can not increase the database version
                    .addCallback(roomCallback)
            .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override // calls when all the tables was created
        public void onCreate (@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private BillsDao billsDao;

        private PopulateDBAsyncTask(AppDB appDB){
            this.billsDao = appDB.billsDao();
        }

        @Override
        protected Void doInBackground (Void... voids) {
            return null;
        }
    }
}
