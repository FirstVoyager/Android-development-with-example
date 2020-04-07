package limitless.android.androiddevelopment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import limitless.android.androiddevelopment.CustomView.CircleImageView;
import limitless.android.androiddevelopment.Interface.ObjectListener;
import limitless.android.androiddevelopment.Model.ModelUser;
import limitless.android.androiddevelopment.Other.Tools;
import limitless.android.androiddevelopment.R;

public class AdapterUserMessage extends RecyclerView.Adapter<AdapterUserMessage.UserViewHolder> {

    private Context context;
    private List<ModelUser> userList;
    private ObjectListener objectListener;

    public AdapterUserMessage(Context context, List<ModelUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.list_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bindView(userList.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return userList.size();
        }catch (Exception e){
            Tools.error(e);
            return 0;
        }
    }

    public void setItemClickListener(ObjectListener objectListener) {
        this.objectListener = objectListener;
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CircleImageView civ;
        private AppCompatTextView tvName, tvDes, tvNotif;
        private View vNotif;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            civ = itemView.findViewById(R.id.imageView_avatar);
            tvName = itemView.findViewById(R.id.textView_name);
            tvDes = itemView.findViewById(R.id.textView_description);
            tvNotif = itemView.findViewById(R.id.textView_notif);
            vNotif = itemView.findViewById(R.id.cardView_notif);
        }

        void bindView(ModelUser u){
            Picasso.get().load(u.imageUrl).resize(120, 120).into(civ);
            tvName.setText(u.name);
            tvDes.setText(u.lastMessage);

            if (u.notif <= 0){
                vNotif.setVisibility(View.GONE);
            }else {
                vNotif.setVisibility(View.VISIBLE);
            tvNotif.setText(String.valueOf(u.notif));
            }
        }

        @Override
        public void onClick(View v) {
            if (objectListener != null)
                objectListener.object(userList.get(getAdapterPosition()));
        }
    }

}
