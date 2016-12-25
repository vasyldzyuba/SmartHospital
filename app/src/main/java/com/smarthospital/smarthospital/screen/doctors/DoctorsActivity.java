package com.smarthospital.smarthospital.screen.doctors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarthospital.smarthospital.R;
import com.smarthospital.smarthospital.model.Doctor;
import com.smarthospital.smarthospital.model.Hospital;
import com.smarthospital.smarthospital.model.Ward;
import com.smarthospital.smarthospital.screen.doctorDetails.DoctorDetailsActivity;
import com.smarthospital.smarthospital.ui.ListVerticalSpacingItemDecoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by V on 12/10/2016.
 */

public class DoctorsActivity extends AppCompatActivity {
    private static final String EXTRA_WARD = "ward";

    public static Intent getStartIntent(Context context, Ward ward) {
        Intent intent = new Intent(context, DoctorsActivity.class);
        intent.putExtra(EXTRA_WARD, ward);
        return intent;
    }

    private static final String TAG = "DoctorsActivity";

    private DoctorsAdapter mDoctorAdapter;

    private DatabaseReference mDatabaseReference;


    private final ValueEventListener mValueEventLIstener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.d(TAG, dataSnapshot.toString());
            List<Doctor> doctors = new ArrayList<Doctor>();
            Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
            Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
            while (iterator.hasNext()) {
                Doctor doctor = iterator.next().getValue(Doctor.class);
                doctors.add(doctor);
            }
            mDoctorAdapter.refreshDoctors(doctors);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration =  new ListVerticalSpacingItemDecoration(getResources().getDimensionPixelSize(R.dimen.item_hospital_margin));
        recyclerView.addItemDecoration(itemDecoration);
        mDoctorAdapter = new DoctorsAdapter(recyclerView.getContext());
        mDoctorAdapter.setOnItemClickListener(new DoctorsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Doctor doctor) {
                startActivity(DoctorDetailsActivity.getStartIntent(DoctorsActivity.this, doctor));
            }
        });
        recyclerView.setAdapter(mDoctorAdapter);
        Ward ward = getIntent().getParcelableExtra(EXTRA_WARD);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("doctors").child(ward.getId());

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseReference.addValueEventListener(mValueEventLIstener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDatabaseReference.removeEventListener(mValueEventLIstener);
    }
}
