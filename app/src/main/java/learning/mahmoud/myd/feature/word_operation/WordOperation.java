package learning.mahmoud.myd.feature.word_operation;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoud.myd.Constants;
import learning.mahmoud.myd.R;
import learning.mahmoud.myd.datalayer.Word;

public class WordOperation extends AppCompatActivity {

    private static final String TAG = "WordOperation";
    WordViewModel wordViewModel;
    boolean isUpdateCase;
    @BindView(R.id.edWord)
    TextInputEditText edWord;
    @BindView(R.id.edMean)
    TextInputEditText edMean;
    @BindView(R.id.edSentence)
    TextInputEditText edSentence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_operation);
        ButterKnife.bind(this);

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        Intent intent = getIntent();

        setTitle("Add");

        if (intent.hasExtra(Constants.WORD_ID)) {
            isUpdateCase = true;
            setTitle("Update");
            wordViewModel.setId(intent.getLongExtra(Constants.WORD_ID, 1));
            wordViewModel.setWord(intent.getStringExtra(Constants.WORD_WORD));
            wordViewModel.setMean(intent.getStringExtra(Constants.WORD_MEAN));
            wordViewModel.setSentence(intent.getStringExtra(Constants.WORD_STATEMENT));
            loadDataToUI();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        }


    }

    private void loadDataToUI() {
        edWord.setText(wordViewModel.getWord());
        edMean.setText(wordViewModel.getMean());
        edSentence.setText(wordViewModel.getSentence());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflaterm = getMenuInflater();
        menuInflaterm.inflate(R.menu.word_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_save:
                saveWord();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveWord() {
        String word = edWord.getText().toString();
        if (word.length() == 0) {
            edWord.setError("Please Enter Word");
            return;
        }

        String mean = edMean.getText().toString();
        if (mean.length() == 0) {
            edMean.setError("Please Enter edMean");
            return;
        }

        String senstence = edSentence.getText().toString();

        Word wordObj = new Word(word, mean, senstence);

        if (isUpdateCase) {
            wordObj.setId(wordViewModel.getId());
            wordViewModel.updateWord(wordObj);
            showMessage("Updated Successfully");
        } else {
            wordViewModel.addWord(wordObj);
            showMessage("Added Successfully");
        }

        finish();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
