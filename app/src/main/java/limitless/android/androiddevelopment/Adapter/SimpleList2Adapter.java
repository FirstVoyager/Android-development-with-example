package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class SimpleList2Adapter extends RecyclerView.Adapter<SimpleList2Adapter.StringViewHolder> {
    private Context context;
    private List<String> strings;

    public SimpleList2Adapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StringViewHolder(LayoutInflater.from(context).inflate(R.layout.simple_list_2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, int position) {
        holder.bindView(strings.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return strings.size();
        }catch (Exception e) {
            Tools.error(e);
            return 0;
        }
    }

    public void deleteItem(int position) {
        strings.remove(position);
        notifyItemRemoved(position);
    }

    public class StringViewHolder extends RecyclerView.ViewHolder{
        private AppCompatTextView tv;
        public View vBack, bFront;
        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            vBack = itemView.findViewById(R.id.linearLayout_back);
            bFront = itemView.findViewById(R.id.linearLayout_front);
            tv = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tools.toast(context, strings.get(getAdapterPosition()));
                }
            });
        }

        void bindView(String s){
            tv.setText(s);
        }



    }

}
