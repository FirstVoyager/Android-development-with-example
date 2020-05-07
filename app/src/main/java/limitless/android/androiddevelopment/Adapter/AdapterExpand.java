package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Model.Section;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class AdapterExpand extends RecyclerView.Adapter<AdapterExpand.ExpandViewHolder> {

    private Context context;
    private List<Section.Inner> inners;

    public AdapterExpand(Context context, List<Section.Inner> inners) {
        this.context = context;
        this.inners = inners;
    }

    @NonNull
    @Override
    public ExpandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpandViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExpandViewHolder holder, int position) {
        holder.bindView(inners.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return inners.size();
        }catch (Exception e){
            Tools.error(e);
            return 0;
        }
    }

    class ExpandViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatTextView tvName;
        private View viewNew;
        public ExpandViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.textView);
            viewNew = itemView.findViewById(R.id.view_new);
        }

        void bindView(Section.Inner im){
            tvName.setText(im.name);
            if (im.isNew)
                viewNew.setVisibility(View.VISIBLE);
            else
                viewNew.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.linearLayout){
                if (inners.get(getAdapterPosition()).aClass1 == null){
                    Tools.customToast(context, R.string.coming_soon);
                }else {
                    Intent intent = new Intent(context, inners.get(getAdapterPosition()).aClass1);
                    intent.putExtra(Intent.EXTRA_TEXT, getAdapterPosition());
                    Tools.startActivity(context, intent);
                }
            }
        }
    }

}
