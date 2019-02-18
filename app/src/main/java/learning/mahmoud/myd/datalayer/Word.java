package learning.mahmoud.myd.datalayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private long id ;
    private String word ;
    private String mean ;
    private String sentence ;

    public Word(String word, String mean, String sentence) {
        this.word = word;
        this.mean = mean;
        this.sentence = sentence;
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

    public String getMean() {
        return mean;
    }

    public String getSentence() {
        return sentence;
    }
}
