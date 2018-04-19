package com.example.apoorv.policyhandler;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Apoorv on 04-04-2018.
 */

public class HomeFragment extends Fragment {
    View myview;
    TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.home_layout,container,false);
        return myview;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
        SharedPreferences spref=getActivity().getSharedPreferences("sp12", Context.MODE_PRIVATE);
        String email=spref.getString("username",null);
        TextView tv=(TextView)getActivity().findViewById(R.id.textView4);
        tv.setText("Welcome"+email);


    }
}
