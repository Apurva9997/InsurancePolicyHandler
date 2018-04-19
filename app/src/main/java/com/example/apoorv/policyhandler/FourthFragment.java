package com.example.apoorv.policyhandler;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

public class FourthFragment extends Fragment {
    View myview;
    DatabaseReference dbr,dbr2;
    DatabaseReference myref,myref2;
    EditText ed7,ed9,ed10;
    TextView tv4;
    Button b6;
    String email;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.fourth_layout,container,false);
        return myview;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Update Profile");
        ed7=(EditText)getActivity().findViewById(R.id.editText7);
        ed9=(EditText)getActivity().findViewById(R.id.editText9);
        ed10=(EditText)getActivity().findViewById(R.id.editText10);
        tv4=(TextView)getActivity().findViewById(R.id.textView5);
        b6=(Button)getActivity().findViewById(R.id.button16);
        SharedPreferences spref=getActivity().getSharedPreferences("sp12", Context.MODE_PRIVATE);
        email=spref.getString("username",null);
        if(!TextUtils.isEmpty(email) && !(email==null)){
        tv4.setText(email);
        /*dbr = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childref = dbr.child("users");
        childref.addValueEventListener(new ValueEventListener() {
            @Override
            *//*public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dds:dataSnapshot.getChildren()){
                    if(dds.hasChild(email)){
                        if(dds.getKey().toString().equals("email"))
                            ed7.setText(dds.getValue().toString());
                        if(dds.getKey().toString().equals("dob"))
                            ed7.setText(dds.getValue().toString());
                        if(dds.getKey().toString().equals("address"))
                            ed7.setText(dds.getValue().toString());
                    }
                }
            }*//*

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/}
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=ed7.getText().toString();
                String dob=ed9.getText().toString();
                String address=ed10.getText().toString();
                dbr = FirebaseDatabase.getInstance().getReference();
                DatabaseReference childref = dbr.child("users");
                childref.child(name).child("email").setValue(email);
                childref.child(name).child("dob").setValue(dob);
                childref.child(name).child("address").setValue(address);
                Toast.makeText(getActivity(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
