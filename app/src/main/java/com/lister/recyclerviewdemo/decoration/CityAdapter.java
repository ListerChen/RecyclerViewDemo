
package com.lister.recyclerviewdemo.decoration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lister.recyclerviewdemo.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private Context mContext;
    private List<CityInfo> mCityList;

    public CityAdapter(Context context, @NonNull List<CityInfo> cityList) {
        this.mContext = context;
        this.mCityList = cityList;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.bindData(mCityList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewCityName;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewCityName = itemView.findViewById(R.id.item_city_text);
        }

        void bindData(CityInfo cityInfo) {
            mTextViewCityName.setText(cityInfo.getCityName());
        }
    }

}
