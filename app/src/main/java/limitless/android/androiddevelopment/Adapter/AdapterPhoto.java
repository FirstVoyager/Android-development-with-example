package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Model.PhotoModel;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class AdapterPhoto extends RecyclerView.Adapter<AdapterPhoto.PhotoViewHolder> {

    private Context context;
    private List<PhotoModel> list;
    private StringListener stringListener;

    public AdapterPhoto(Context context, List<PhotoModel> list, StringListener stringListener) {
        this.context = context;
        this.list = list;
        this.stringListener = stringListener;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.recyler_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
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

    public void setData(List<PhotoModel> models){
        if (models == null)
            return;
        if (list == null)
            list = new ArrayList<>();
        list.clear();
        list.addAll(models);
        notifyDataSetChanged();
    }

    public void clearAll() {
        if (list != null){
            list.clear();
            notifyDataSetChanged();
        }
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatImageView ivMain;
        private AppCompatTextView tvTitle;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivMain = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.textView_title);
        }

        void bindView(PhotoModel pm){
            Tools.loadImage(context, pm.data, ivMain);
            tvTitle.setText(pm.title);
        }

        @Override
        public void onClick(View v) {
            if (stringListener != null){
                stringListener.string(list.get(getAdapterPosition()).data);
            }
        }
    }

}
