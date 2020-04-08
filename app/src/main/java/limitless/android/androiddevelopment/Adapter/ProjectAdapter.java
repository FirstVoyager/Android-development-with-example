package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Model.ProjectModel;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private Context context;
    public List<ProjectModel> list;

    public ProjectAdapter(Context context, List<ProjectModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProjectViewHolder(LayoutInflater.from(context).inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return list.size();
        }catch (Exception e){
            Tools.error(e);
            return 0;
        }
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatImageView ivBack;
        private MaterialTextView tvTitle, tvPrice, tvCaption;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBack = itemView.findViewById(R.id.imageView_background);
            tvTitle = itemView.findViewById(R.id.textView_title);
            tvPrice = itemView.findViewById(R.id.textView_price);
            tvCaption = itemView.findViewById(R.id.textView_caption);
            itemView.setOnClickListener(this);
        }

        void bindView(ProjectModel pm){
            tvPrice.setText(String.valueOf(pm.price));
            tvPrice.append("$");
            tvTitle.setText(pm.title);
            tvCaption.setText(pm.caption);
            Tools.loadImage(context, pm.image, ivBack);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cardView_project){

            }
        }
    }

}
