package com.smarthospital.smarthospital.screen.doctors;

/**
 * Created by V on 12/10/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthospital.smarthospital.R;
import com.smarthospital.smarthospital.model.Doctor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<Doctor> mDoctorList;

    public interface OnItemClickListener {
        void onItemClick(Doctor doctor);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public DoctorsAdapter(Context Doctors) {
        mLayoutInflater = LayoutInflater.from(Doctors);
        mDoctorList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemDoctor = mLayoutInflater.inflate(R.layout.item_doctor, parent, false);
        return new ViewHolder(itemDoctor);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Doctor doctor = mDoctorList.get(position);
        holder.bindDoctor(doctor);
    }

    @Override
    public int getItemCount() {
        return mDoctorList.size();
    }

    public void refreshDoctors(List<Doctor> doctors) {
        mDoctorList.clear();
        mDoctorList.addAll(doctors);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;
        private final TextView mNameTextView;
        private final TextView mSpecialityTextView;

        private Doctor mDoctor;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.photo_image_view);
            mNameTextView = (TextView) itemView.findViewById(R.id.text_view_name);
            mSpecialityTextView = (TextView) itemView.findViewById(R.id.text_view_speciality);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(mDoctor);
                    }
                }
            });
        }

        void bindDoctor(Doctor doctor) {
            mDoctor = doctor;
            mNameTextView.setText(mDoctor.getName());
            mSpecialityTextView.setText(mDoctor.getSpeciality());
            Picasso.with(mImageView.getContext())
                    .load(mDoctor.getPhoto().getUrl())
                    .placeholder(R.color.grey_400)
                    .into(mImageView);
        }
    }
}