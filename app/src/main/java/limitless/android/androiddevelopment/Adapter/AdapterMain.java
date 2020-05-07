package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Model.Section;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.MainViewHolder> {

    private Context context;
    private List<Section> mainList;

    public AdapterMain(Context context, List<Section> mainList) {
        this.context = context;
        this.mainList = mainList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bindView(mainList.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return mainList.size();
        }catch (Exception e){
            Tools.error(e);
            return 0;
        }
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatImageView ivMain, ivNavigate;
        private MaterialTextView tvName;
        private View viewNew;
        private RecyclerView recyclerView;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMain = itemView.findViewById(R.id.imageView_icon);
            ivNavigate = itemView.findViewById(R.id.imageView_navigate);
            tvName = itemView.findViewById(R.id.textView_title);
            viewNew = itemView.findViewById(R.id.view_new);
            recyclerView = itemView.findViewById(R.id.recyclerView_recycler);

            itemView.setOnClickListener(this);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setVisibility(View.GONE);
        }

        void bindView(Section mm){
            recyclerView.setVisibility(View.GONE);
            ivNavigate.setVisibility(View.GONE);
            if (mm.hasData)
                ivNavigate.setVisibility(View.VISIBLE);

            Tools.loadImage(context, mm.image, ivMain);
            tvName.setText(mm.name);
            if (mm.hasNew)
                viewNew.setVisibility(View.VISIBLE);
            else
                viewNew.setVisibility(View.INVISIBLE);

            if (mm.hasData) {
                if (mm.showData){
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new AdapterExpand(context, mm.innerList));
                }else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    recyclerView.setAdapter(null);
                }
            }else {
                recyclerView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            Section mm = mainList.get(getAdapterPosition());
            if (mm.aClass != null) {
                Tools.startActivity(context, new Intent(context, mm.aClass));
                return;
            }
            if (mm.innerList == null || mm.innerList.size() <= 0){
                Tools.customToast(context, R.string.coming_soon);
                return;
            }
            if (mm.showData){
                recyclerView.setAdapter(null);
                recyclerView.setVisibility(View.GONE);
            }else {
                recyclerView.setAdapter(new AdapterExpand(context, mm.innerList));
                recyclerView.setVisibility(View.VISIBLE);
            }
            mm.showData = ! mm.showData;
        }
    }

}
