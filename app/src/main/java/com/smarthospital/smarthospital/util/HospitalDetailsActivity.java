package com.smarthospital.smarthospital.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.smarthospital.smarthospital.R;

/**
 * Created by HOME-PC on 03.12.2016.
 */


public class HospitalDetailsActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static Intent getStartIntent(Context context) {
        Intent startIntent = new Intent(context, HospitalDetailsActivity.class);
        return startIntent;
    }

    ImageView mImageView;

    TextView mNameTextView;

    TextView mAddressTextView;

    TextView mNumberTextView;

    TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        mImageView = (ImageView) findViewById(R.id.image_view);
        mNameTextView = (TextView) findViewById(R.id.text_view_name);
        mAddressTextView = (TextView) findViewById(R.id.text_view_address);
        mNumberTextView = (TextView) findViewById(R.id.text_view_phone_number);
        mDescriptionTextView = (TextView) findViewById(R.id.text_view_description);
    }
}


