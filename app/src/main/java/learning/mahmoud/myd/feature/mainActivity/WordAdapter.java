package learning.mahmoud.myd.feature.mainActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoud.myd.R;
import learning.mahmoud.myd.datalayer.Word;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.Holder> {


    private List<Word> list;

    public WordAdapter() {
        list = new ArrayList<>();
    }

    public void addWord(Word item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
        notifyItemRangeChanged(list.size() - 1, list.size());
    }


    public void setWordList(List<Word> newList) {
        list = new ArrayList<>(newList);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<Word> getList() {
        return list;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.word_item, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Word word = list.get(i);
        holder.tvWord.setText(word.getWord());
        holder.tvWordMean.setText(word.getMean());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvWord)
        TextView tvWord;
        @BindView(R.id.tvWordMean)
        TextView tvWordMean;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}