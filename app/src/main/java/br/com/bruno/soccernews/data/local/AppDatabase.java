package br.com.bruno.soccernews.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.bruno.soccernews.domain.News;

@Database(entities =  {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract NewsDAO newsDAO();
}
