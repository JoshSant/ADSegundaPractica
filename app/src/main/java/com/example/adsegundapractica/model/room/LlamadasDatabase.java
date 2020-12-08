package com.example.adsegundapractica.model.room;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.adsegundapractica.model.dao.LlamadasDao;
import com.example.adsegundapractica.model.entity.LLamadas;

import static com.example.adsegundapractica.AmigoApplication.EjecutorDeHilos;

@Database(entities = {LLamadas.class}, version = 1, exportSchema = false)
public abstract class LlamadasDatabase extends RoomDatabase {

    public abstract LlamadasDao getLlamadasDao();
    private volatile static LlamadasDatabase INSTANCE;

    public static LlamadasDatabase getDatabase(Application context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LlamadasDatabase.class, "dbllamada")
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            EjecutorDeHilos.execute(() -> {
                LlamadasDao dao = INSTANCE.getLlamadasDao();
                dao.deleteAll();
            });
        }
    };

    public static LlamadasDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LlamadasDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LlamadasDatabase.class, "dbllamada").addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
