package learning.mahmoud.myd.feature.mainActivity;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import learning.mahmoud.myd.datalayer.Repository;
import learning.mahmoud.myd.datalayer.Word;

public class MainViewModel extends AndroidViewModel {

    private Repository repository ;

    private LiveData<List<Word>> wordList ;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        wordList = repository.getWordList();
    }


    public LiveData<List<Word>> getWordList() {
        return wordList;
    }
}
