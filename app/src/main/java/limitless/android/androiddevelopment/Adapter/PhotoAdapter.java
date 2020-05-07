package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Interface.Listener;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Model.Photo;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context context;
    private List<Photo> list;
    private Listener<Uri> uriListener;

    public PhotoAdapter(Context context, List<Photo> list, Listener<Uri> uriListener) {
        this.context = context;
        this.list = list;
        this.uriListener = uriListener;
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

    public void setData(List<Photo> models){
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

        void bindView(Photo pm){
            Tools.loadImage(context, pm.uri, ivMain);
            tvTitle.setText(pm.title);
        }

        @Override
        public void onClick(View v) {
            if (uriListener != null){
                uriListener.data(list.get(getAdapterPosition()).uri);
            }
        }
    }

}
