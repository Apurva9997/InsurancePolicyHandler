package com.example.apoorv.policyhandler;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
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

public class SecondFragment extends Fragment {
    View myview;
    Button b4;
    EditText ed8;
    DatabaseReference dbr;
    DatabaseReference myref;
    Button b1;EditText ed1;
    TextView tv3;
    String out="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.second_layout,container,false);
        return myview;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Policy Search");
        b4=(Button)getView().findViewById(R.id.button4);
        ed8=(EditText)getView().findViewById(R.id.editText8);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = ed8.getText().toString();
                myref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference childref = myref.child("policies");
                childref=childref.child(key);
                childref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //String data = dataSnapshot.getValue(String.class);
                        for(DataSnapshot d:dataSnapshot.getChildren()){
                            out+=d.getKey()+"\t\t\t\t"+d.getValue()+"\n";
                        }
                        //Toast.makeText(getActivity(), "Msg : "+out, Toast.LENGTH_SHORT).show();
                        if(!TextUtils.isEmpty(out)){
                            /*Bundle bundle = new Bundle();
                            bundle.putString("params", out);
                            SeventhFragment myObj = new SeventhFragment();
                            myObj.setArguments(bundle);
                            FragmentManager fragmentManager=getFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.content_frame,myObj).commit();*/
                                Bundle args = new Bundle();
                                args.putString("param",out);
                                Fragment seventhFragment = new SeventhFragment();
                                seventhFragment.setArguments(args);
                                getFragmentManager().beginTransaction().replace(R.id.content_frame,seventhFragment).commit();
                        }
                        else {
                            Toast.makeText(getActivity(), "Data not available", Toast.LENGTH_SHORT).show();
                        }}
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
