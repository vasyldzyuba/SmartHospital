package com.smarthospital.smarthospital.screen.hospitalDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.smarthospital.smarthospital.R;
import com.smarthospital.smarthospital.model.Hospital;
import com.smarthospital.smarthospital.model.Ward;
import com.squareup.picasso.Picasso;


public class HospitalDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_HOSPITAL = "hospital";

    public static Intent getStartIntent(Context context, Hospital hospital) {
        Intent startIntent = new Intent(context, HospitalDetailsActivity.class);
        startIntent.putExtra(EXTRA_HOSPITAL, hospital);
        return startIntent;
    }


    ImageView mImageView;

    //  TextView mNameTextView;

    TextView mAddressTextView;

    TextView mNumberTextView;

    TextView mDescriptionTextView;

    Hospital mHospital;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        mHospital = getIntent().getParcelableExtra(EXTRA_HOSPITAL);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mHospital.getName());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        mImageView = (ImageView) findViewById(R.id.image_view);
        // mNameTextView = (TextView) findViewById(R.id.text_view_name);
        mAddressTextView = (TextView) findViewById(R.id.text_view_address);
        mNumberTextView = (TextView) findViewById(R.id.text_view_phone_number);
        mDescriptionTextView = (TextView) findViewById(R.id.text_view_description);
        Button wards = (Button) findViewById(R.id.button_wards);
        wards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WardsDialog wardsDialog = WardsDialog.newInstance(mHospital.getWards());
                wardsDialog.show(getSupportFragmentManager(), "");
            }
        });
        bindHospital(mHospital);
    }

    private void bindHospital(Hospital hospital) {
        Picasso.with(mImageView.getContext())
                .load(hospital.getImage().getUrl())
                .into(mImageView);
        //  mNameTextView.setText(hospital.getName());
        mAddressTextView.setText(hospital.getLocation().getAddress());
        mDescriptionTextView.setText(hospital.getDescription());
    }
}