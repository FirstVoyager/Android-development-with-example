package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class HorizontalListAdapter extends RecyclerView.Adapter<HorizontalListAdapter.HorizontalViewHolder> {
    private Context context;
    private List<String> strings = new ArrayList<>();
    private List<Integer> integers = new ArrayList<>();

    public HorizontalListAdapter(Context context, HashMap<String, Integer> hashMap) {
        this.context = context;
        this.strings.addAll(hashMap.keySet());
        this.integers.addAll(hashMap.values());
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HorizontalViewHolder(LayoutInflater.from(context).inflate(R.layout.horizontal_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        holder.bindView(integers.get(position), strings.get(position));
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

    class HorizontalViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView iv;
        private AppCompatTextView tv;
        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageView);
            tv = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tools.toast(context, strings.get(getAdapterPosition()));
                }
            });
        }

        void bindView(int resId, String s){
            Tools.loadImage(context, resId, iv);
            tv.setText(s);
        }

    }

}
