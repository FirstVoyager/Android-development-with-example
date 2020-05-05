package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.TextViewHolder> {

    private Context context;
    private List<String> strings;

    public SimpleTextAdapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(context).inflate(
                R.layout.recycler_simple_text, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bindView(strings.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return strings.size();
        }catch (Exception e){
            Tools.error(e);
            return 0;
        }
    }

    public void clear() {
        if (strings == null)
            strings = new ArrayList<>();
        strings.clear();
        notifyDataSetChanged();
    }

    public void setData(List<String> list) {
        if (list == null)
            return;
        strings = list;
        notifyDataSetChanged();
    }

    class TextViewHolder extends RecyclerView.ViewHolder{
        private AppCompatTextView tv;
        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
        }

        void bindView(String s){
            tv.setText(s);
        }

    }

}
