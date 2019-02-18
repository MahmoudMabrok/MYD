package learning.mahmoud.myd.feature.word_operation;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import learning.mahmoud.myd.datalayer.Repository;
import learning.mahmoud.myd.datalayer.Word;

public class WordViewModel extends AndroidViewModel {

    // used to hold values in case of updating word (these values comes from MainActivity )
    private long id;
    private String word;
    private String mean;
    private String sentence;

    private Repository repository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


    public void addWord(Word word) {
        repository.insertWord(word);
    }

    public void updateWord(Word word) {
        repository.updateWord(word);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
