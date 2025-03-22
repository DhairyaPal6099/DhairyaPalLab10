package dhairya.pal.n01576099.dp.ui;// Dhairya Pal N01576099

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dhairya.pal.n01576099.dp.R;

public class ItemModelAdapter extends RecyclerView.Adapter<ItemModelAdapter.ViewHolder> {
    private ArrayList<ItemModel> itemModelArrayList;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    public ItemModelAdapter(ArrayList<ItemModel> itemModelArrayList, Context context, OnItemClickListener listener) {
        this.itemModelArrayList = itemModelArrayList;
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView videoTitle;
        private ImageView videoIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            videoTitle = itemView.findViewById(R.id.dhaTextViewVideoTitle);
            videoIcon = itemView.findViewById(R.id.dhaImageViewVideoIcon);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemModelAdapter.ViewHolder holder, int position) {
        ItemModel itemModel = itemModelArrayList.get(position);
        holder.videoTitle.setText(itemModel.getVideoTitle());
        holder.videoIcon.setImageResource(itemModel.getImageResId());
    }

    @Override
    public int getItemCount() {
        return itemModelArrayList.size();
    }
}
