package com.example.ptsganjil202111rpl1melvin30;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ListViewHolder> {

    private ArrayList<TeamModel> dataList;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public FavoriteAdapter(Context mContext, ArrayList<TeamModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_list, parent, false);
        return new ListViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.txt_title.setText(dataList.get(position).getTitle());
        Picasso.get()
                .load(dataList.get(position).getImage())
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(holder.img_thumbnail);
        holder.txt_rating.setText(dataList.get(position).getVoteAverage());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_title, txt_rating;
        private ImageView img_thumbnail, img_Favorite;
        private Boolean isFavorite;
        private RelativeLayout relativeLayout;

        public ListViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            img_Favorite = itemView.findViewById(R.id.img_favorite);
            txt_title = itemView.findViewById(R.id.txt_title);
            img_thumbnail = itemView.findViewById(R.id.img_thumbnail);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            txt_rating = itemView.findViewById(R.id.txt_rating);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });



        }
    }
}


