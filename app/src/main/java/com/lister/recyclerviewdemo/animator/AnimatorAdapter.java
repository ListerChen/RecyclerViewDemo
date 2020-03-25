
package com.lister.recyclerviewdemo.animator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lister.recyclerviewdemo.R;

import java.util.List;

public class AnimatorAdapter extends RecyclerView.Adapter<AnimatorAdapter.AnimatorViewHolder> {

    private Context mContext;
    private List<String> mData;

    public AnimatorAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public AnimatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_animator, parent, false);
        return new AnimatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimatorViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class AnimatorViewHolder extends RecyclerView.ViewHolder {

        TextView mAnimatorText;

        public AnimatorViewHolder(@NonNull View itemView) {
            super(itemView);
            mAnimatorText = itemView.findViewById(R.id.item_animator_text);
        }

        void bindData(String text) {
            mAnimatorText.setText(text);
        }
    }

}
