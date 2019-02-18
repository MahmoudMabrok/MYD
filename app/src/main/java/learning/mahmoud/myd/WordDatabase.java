package learning.mahmoud.myd;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import learning.mahmoud.myd.datalayer.Word;
import learning.mahmoud.myd.datalayer.WordDao;

@Database(entities = {Word.class} , version = 1 )
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase instance = null ;
    // Room will implement it
    public abstract WordDao wordDao();

    public static synchronized  WordDatabase getInstance(Application application){

        if (instance == null) {

            instance = Room.databaseBuilder(application , WordDatabase.class , "worddb")
                    .fallbackToDestructiveMigration()
                    .addCallback( populateCallback)
                    .build() ;
        }
        return instance ;
    }


    private static  RoomDatabase.Callback populateCallback = new RoomDatabase.Callback() {
        private WordDao wordDao ;


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateAsyncTask(instance).execute();
        }
    };



    private static class populateAsyncTask extends AsyncTask<Void , Void , Void> {
        WordDao wordDao ;
        public populateAsyncTask(WordDatabase wordDatabase) {
            super();
            wordDao = wordDatabase.wordDao() ;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.insertWord(new Word("Dog" , "animal that eat bine" , "dog is beautiful "));
            wordDao.insertWord(new Word("Dog" , "animal that eat bine" , "dog is beautiful "));
            wordDao.insertWord(new Word("Dog" , "animal that eat bine" , "dog is beautiful "));
            return null;
        }
    }

}
