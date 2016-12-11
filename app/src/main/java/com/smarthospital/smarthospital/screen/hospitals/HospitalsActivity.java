package com.smarthospital.smarthospital.screen.hospitals;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ViewSwitcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarthospital.smarthospital.R;
import com.smarthospital.smarthospital.model.Hospital;
import com.smarthospital.smarthospital.screen.hospitalDetails.HospitalDetailsActivity;
import com.smarthospital.smarthospital.screen.entry.SignInActivity;
import com.smarthospital.smarthospital.screen.maps.MapsActivity;
import com.smarthospital.smarthospital.ui.ListVerticalSpacingItemDecoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HospitalsActivity extends AppCompatActivity {

    private static final String TAG = "HospitalsActivity";

    private DatabaseReference mDatabaseReference;

    private ViewSwitcher mViewSwitcher;

    private FloatingActionButton mMapButton;

    //Викликає метод Main Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startSignInActivity();
            return;
        }

        mViewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);
        mMapButton = (FloatingActionButton) findViewById(R.id.map_button);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MapsActivity.getStartIntent(HospitalsActivity.this));
            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration =
                new ListVerticalSpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.item_hospital_margin));
        recyclerView.addItemDecoration(itemDecoration);
        final HospitalsAdapter adapter = new HospitalsAdapter(this);
        adapter.setOnItemClickListener(new HospitalsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Hospital hospital) {
                startActivity(HospitalDetailsActivity.getStartIntent(HospitalsActivity.this, hospital));
            }
        });
        recyclerView.setAdapter(adapter);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("hospitals");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());

                List<Hospital> hospitals = new ArrayList<Hospital>();
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()) {
                    Hospital hospital = iterator.next().getValue(Hospital.class);
                    hospitals.add(hospital);
                }
                adapter.refreshHospitals(hospitals);
                mViewSwitcher.setDisplayedChild(1);
                mMapButton.show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());

            }
        });
    }

    private void startSignInActivity() {
        startActivity(SignInActivity.getStartIntent(HospitalsActivity.this));
        finish();
    }
}
