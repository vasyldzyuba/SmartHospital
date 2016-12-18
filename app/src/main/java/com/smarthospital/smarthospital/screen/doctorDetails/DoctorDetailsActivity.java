package com.smarthospital.smarthospital.screen.doctorDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthospital.smarthospital.R;
import com.smarthospital.smarthospital.model.Doctor;
import com.squareup.picasso.Picasso;


public class DoctorDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_DOCTOR = "doctor";

    public static Intent getStartIntent(Context context, Doctor doctor) {

        Intent startIntent = new Intent(context, DoctorDetailsActivity.class);
        startIntent.putExtra(EXTRA_DOCTOR, doctor);
        return startIntent;
    }

    ImageView mImageView;

    TextView mNameTextView;

    TextView mSpecialityTextView;

    TextView mBioTextView;

    Doctor mDoctor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        mDoctor = getIntent().getParcelableExtra(EXTRA_DOCTOR);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mDoctor.getName());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mImageView = (ImageView) findViewById(R.id.photo_image_view);
        mNameTextView = (TextView) findViewById(R.id.text_view_name);
        mSpecialityTextView = (TextView) findViewById(R.id.text_view_speciality);
        mBioTextView = (TextView) findViewById(R.id.text_view_bio);

        bindHospital(mDoctor);
    }

    private void bindHospital(Doctor doctor) {
        Picasso.with(mImageView.getContext())
                .load(doctor.getPhoto().getUrl())
                .into(mImageView);
        mNameTextView.setText(doctor.getName());
        mSpecialityTextView.setText(doctor.getSpeciality());
        mBioTextView.setText(doctor.getBio());

    }
}
