package com.example.apoorv.policyhandler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.time.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Apoorv on 04-04-2018.
 */

public class ThirdFragment extends Fragment {
    View myview;
    DatabaseReference myref;
    DatabaseReference dbr;
    String[] todayarr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.third_layout,container,false);
        return myview;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("View Dues");
        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate)); //2016/11/16;
        String today=dtf.format(localDate);
        todayarr=today.split("/");*/
        dbr= FirebaseDatabase.getInstance().getReference();
        myref=dbr.child("policies");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ddds:dataSnapshot.getChildren()) {
                    for (DataSnapshot dds:ddds.getChildren()){
                        if (dds.getKey().toString().equals("commencementDate")) {
                            String[] cdate = dds.getValue().toString().split("/");
                            //System.out.println("pppppppppppppppppppppppppppppppp" + cdate[0] + "05" + cdate[0] + "04");
                            if (cdate[0].equals("05")) {
                                Toast.makeText(getActivity(), ddds.getKey().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
