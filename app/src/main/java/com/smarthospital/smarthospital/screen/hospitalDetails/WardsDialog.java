package com.smarthospital.smarthospital.screen.hospitalDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthospital.smarthospital.R;
import com.smarthospital.smarthospital.model.Ward;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME-PC on 11.12.2016.
 */

public class WardsDialog extends BottomSheetDialogFragment {

    private static final String ARGUMENT_WARDS = "ARGUMENT_WARDS";


    public static WardsDialog newInstance(List<Ward> wards) {

        WardsDialog nazar = new WardsDialog();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARGUMENT_WARDS, new ArrayList<>(wards));
        nazar.setArguments(args);
        return nazar;
    }

    public interface OnItemClickListener{
        void onItemClick(Ward ward);

    }
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.dialog_wards, container, false);
        List<Ward> wards = getArguments().getParcelableArrayList(ARGUMENT_WARDS);

                for (final Ward wd : wards){
                    TextView textView = (TextView) inflater.inflate(R.layout.item_ward, rootView, false);
                    rootView.addView(textView);
                    textView.setText(wd.getName());
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick(wd);
                            }
                        }
                    });
                }
        return rootView;

    }

}
