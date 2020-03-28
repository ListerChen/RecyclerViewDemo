
package com.lister.recyclerviewdemo.turntable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lister.recyclerviewdemo.R;

import java.util.List;

public class TurntableAdapter extends RecyclerView.Adapter<TurntableAdapter.TurntableViewHolder> {

    private Context mContext;
    private List<Integer> mPics;

    public TurntableAdapter(Context context, List<Integer> pics) {
        this.mContext = context;
        this.mPics = pics;
    }

    @NonNull
    @Override
    public TurntableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_turntable, parent, false);
        return new TurntableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TurntableViewHolder holder, int position) {
        holder.bindData(mPics.get(position));
    }

    @Override
    public int getItemCount() {
        return mPics.size();
    }

    static class TurntableViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TurntableViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_turntable_image);
        }

        void bindData(int res) {
            imageView.setImageResource(res);
        }
    }

}
