package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.Interface.StringListener;
import limitless.android.androiddevelopment.Model.SongModel;
import limitless.android.androiddevelopment.Data.Data;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class AdapterSongs extends RecyclerView.Adapter<AdapterSongs.AudioViewHolder> {
    public Context context;
    public List<SongModel> list;
    private StringListener stringListener;

    public AdapterSongs(Context context, List<SongModel> list, StringListener stringListener) {
        this.context = context;
        this.list = list;
        this.stringListener = stringListener;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AudioViewHolder(LayoutInflater.from(context).inflate(R.layout.recyceler_audio, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
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

    public void setData(List<SongModel> models){
        if (models == null)
            return;
        if (list == null)
            list = new ArrayList<>();
        list.clear();
        list.addAll(models);
        notifyDataSetChanged();
    }

    public void clearAll(){
        if (list == null)
            list = new ArrayList<>();
        list.clear();
        notifyDataSetChanged();
    }

    class AudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatImageView ivCover;
        private AppCompatTextView tvTitle, tvDes;
        private AppCompatImageButton btnMore;
        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivCover = itemView.findViewById(R.id.imageView_cover);
            tvTitle = itemView.findViewById(R.id.textView_title);
            tvDes = itemView.findViewById(R.id.textView_des);
            btnMore = itemView.findViewById(R.id.button_more);
            btnMore.setOnClickListener(this);
        }

        void bindView(SongModel am){
            tvTitle.setText(am.title);
            tvDes.setText(am.artist);
            tvDes.append(", ");
            tvDes.append(am.album);
            ivCover.setImageResource(R.drawable.ic_music_note_black_24dp);
            new GetCoverPhoto(ivCover, getAdapterPosition()).execute(am.data);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button_more){
                showPopupMenu(v);
            }else {
                if (stringListener != null)
                    stringListener.string(list.get(getAdapterPosition()).data);
            }
        }

        private void showPopupMenu(View v) {
            PopupMenu pm = new PopupMenu(context, v);
            pm.inflate(R.menu.menu_adapter_song);
            pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Tools.toast(context, item.getTitle().toString() + " " + list.get(getAdapterPosition()).title);
                    return false;
                }
            });
            MenuPopupHelper helper = new MenuPopupHelper(context, (MenuBuilder) pm.getMenu(), v);
            helper.setForceShowIcon(true);
            helper.show();
        }

        private class GetCoverPhoto extends AsyncTask<String, Void, Bitmap>{

            private AppCompatImageView iv;
            private int position;

            public GetCoverPhoto(AppCompatImageView iv, int position) {
                this.iv = iv;
                this.position = position;
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                Bitmap bitmap = Data.getAudioCoverPhoto(strings[0]);
                if (bitmap == null)
                    return null;
                return Bitmap.createScaledBitmap(bitmap, 95, 95, false);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (bitmap != null){
                    if (getAdapterPosition() == position){
                        iv.setImageBitmap(bitmap);
                    }
                }

            }
        }

    }


}
