package com.example.apoorv.policyhandler;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class SeventhFragment extends Fragment {
    View myview;
    TextView tv3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.seventh_layout,container,false);
        return myview;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Policy Search");
        Bundle b = this.getArguments();
        tv3 = (TextView) getActivity().findViewById(R.id.textView3);
        String data = b.getString("param");
        if(data!=null) {
            //System.out.println("**********" + data + "*************");
            tv3.setText(data);
        }
        else
            Toast.makeText(getActivity(), "Error didn't received any data", Toast.LENGTH_SHORT).show();
    }
}
