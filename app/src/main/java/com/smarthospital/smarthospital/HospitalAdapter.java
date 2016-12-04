package com.smarthospital.smarthospital;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthospital.smarthospital.model.Hospital;
import com.smarthospital.smarthospital.model.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mykola on 03.12.16.
 */

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<Hospital> mHospitalList;

    public HospitalAdapter(Context MYKOLA) {
        mLayoutInflater = LayoutInflater.from(MYKOLA);
        mHospitalList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemHospital = mLayoutInflater.inflate(R.layout.item_hospital, parent , false);
        return new ViewHolder(itemHospital);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hospital hospital = mHospitalList.get(position);
        holder.bindHospital(hospital);
    }

    @Override
    public int getItemCount() {
        return mHospitalList.size();
    }

    public void refreshHospitals(List<Hospital> hospitals){
        mHospitalList.clear();
        mHospitalList.addAll(hospitals);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;
        private final TextView mNameTextView;
        private final TextView mAdressTextView;

        private Hospital mHospital;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.photo_image_view);
            mNameTextView = (TextView) itemView.findViewById(R.id.text_view_name);
            mAdressTextView = (TextView) itemView.findViewById(R.id.text_view_address);
        }

        public void bindHospital(Hospital hospital) {
            mHospital = hospital;
            mNameTextView.setText(mHospital.getName());
            mAdressTextView.setText(mHospital.getLocation().getAddress());
            Picasso.with(mImageView.getContext())
                    .load(mHospital.getImage().getUrl())
                    .into(mImageView);
        }
    }
}
