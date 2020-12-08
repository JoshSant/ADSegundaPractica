package com.example.adsegundapractica.model.room;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.adsegundapractica.model.dao.AmigoDao;
import com.example.adsegundapractica.model.entity.Amigo;

import static com.example.adsegundapractica.AmigoApplication.EjecutorDeHilos;

@Database(entities = {Amigo.class}, version = 1, exportSchema = false)
public abstract class AmigoDatabase extends RoomDatabase {

    public abstract AmigoDao getAmigoDao();
    private volatile static AmigoDatabase INSTANCE;

    public static AmigoDatabase getDatabase(Application context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AmigoDatabase.class, "dbamigo")
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            EjecutorDeHilos.execute(() -> {
                AmigoDao dao = INSTANCE.getAmigoDao();
                dao.deleteAll();
            });
        }
    };

    public static AmigoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AmigoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AmigoDatabase.class, "dbamigo").addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
