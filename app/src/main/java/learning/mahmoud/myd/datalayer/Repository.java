package learning.mahmoud.myd.datalayer;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import learning.mahmoud.myd.WordDatabase;

public class Repository {

    private WordDao  wordDao ;

    private LiveData<List<Word>> mWordList ;

    public Repository(Application application) {
        WordDatabase wordDatabase = WordDatabase.getInstance(application);
        wordDao = wordDatabase.wordDao();
        // as after room database we  will populate it
        // using (callback and asyncTask) so here will be data
        mWordList = wordDao.getAllWords() ;
    }

    public void insertWord(Word word){
        new InsertWordAsyncTask(wordDao).execute(word);
    }

    public void updateWord(Word word) {
        new UpdateWordAsyncTask(wordDao).execute(word);
    }

    public  void deleteWord(Word word){
        new DeleteWordAsyncTask(wordDao).execute(word);
    }

    public LiveData<List<Word>> getWordList() {
        return mWordList;
    }

    public void deleteAll() {

    }


    private static class InsertWordAsyncTask extends AsyncTask<Word ,Void ,Void >{
        private WordDao wordDao ;

         InsertWordAsyncTask(WordDao wordDao) {
            super();
            this.wordDao = wordDao ;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWord(words[0]);
            return null;
        }
    }

    private static class UpdateWordAsyncTask extends AsyncTask<Word ,Void ,Void >{
        private WordDao wordDao ;

        public UpdateWordAsyncTask(WordDao wordDao) {
            super();
            this.wordDao = wordDao ;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWord(words[0]);
            return null;
        }
    }

    private static class DeleteWordAsyncTask extends AsyncTask<Word ,Void ,Void >{
        private WordDao wordDao ;

        public DeleteWordAsyncTask(WordDao wordDao) {
            super();
            this.wordDao = wordDao ;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWord(words[0]);
            return null;
        }
    }


}
