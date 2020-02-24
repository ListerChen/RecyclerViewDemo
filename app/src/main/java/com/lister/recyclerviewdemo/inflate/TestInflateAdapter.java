
package com.lister.recyclerviewdemo.inflate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lister.recyclerviewdemo.R;
import java.util.List;

public class TestInflateAdapter extends RecyclerView.Adapter<TestInflateAdapter.TestInflateViewHolder> {

    private Context mContext;
    private List<String> mData;

    public TestInflateAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public TestInflateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_test_inflate, parent, false);
        return new TestInflateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestInflateViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class TestInflateViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        TestInflateViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.item_test_inflate_text);
        }

        void bindData(String text) {
            mText.setText(text);
        }
    }

}
