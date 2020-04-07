package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class AdapterSimpleListItem extends RecyclerView.Adapter<AdapterSimpleListItem.ItemViewHolder> {

    private Context context;
    private List<String> items;
    private StringListener stringListener;

    public AdapterSimpleListItem(Context context, List<String> items, StringListener stringListener) {
        this.context = context;
        this.items = items;
        this.stringListener = stringListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_simple_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindView(items.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return items.size();
        }catch (Exception e){
            Tools.error(e);
            return 0;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatTextView btn;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            btn  = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        void bindView(String s){
            btn.setText(s);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.linearLayout && stringListener != null){
                stringListener.string(items.get(getAdapterPosition()));
            }
        }
    }

    public static interface StringListener{
        void string(String s);
    }

}
