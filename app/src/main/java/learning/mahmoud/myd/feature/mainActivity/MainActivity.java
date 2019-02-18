package learning.mahmoud.myd.feature.mainActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import learning.mahmoud.myd.Constants;
import learning.mahmoud.myd.R;
import learning.mahmoud.myd.datalayer.Word;
import learning.mahmoud.myd.feature.word_operation.WordOperation;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvWord)
    RecyclerView rvWord;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private MainViewModel mainViewModel;
    WordAdapter wordAdapter = new WordAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initRv();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void initRv() {
        rvWord.setLayoutManager(new LinearLayoutManager(this));
        rvWord.setAdapter(wordAdapter);

        mainViewModel.getWordList().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                wordAdapter.setWordList(words);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int pos = viewHolder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    Word word = wordAdapter.getWordAt(pos);
                    mainViewModel.deleteWord(word);
                }

            }
        }).attachToRecyclerView(rvWord);


        wordAdapter.setWordListner(new WordAdapter.WordLisnter() {
            @Override
            public void onClick(Word word) {
                if (word != null) {
                    Intent intent = new Intent(MainActivity.this,
                            WordOperation.class);
                    intent.putExtra(Constants.WORD_ID, word.getId());
                    intent.putExtra(Constants.WORD_WORD, word.getWord());
                    intent.putExtra(Constants.WORD_MEAN, word.getMean());
                    intent.putExtra(Constants.WORD_STATEMENT, word.getSentence());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete_all_word) {
            mainViewModel.deleteAll();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, WordOperation.class);
        startActivity(intent);
    }

}
