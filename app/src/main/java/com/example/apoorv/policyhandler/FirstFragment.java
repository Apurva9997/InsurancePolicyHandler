package com.example.apoorv.policyhandler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Apoorv on 04-04-2018.
 */

public class FirstFragment extends Fragment {
    View myview;
    DatabaseReference dbr;
    DatabaseReference myref;
    Spinner spinner,spinner2;
    String type;
    String planval;
    ArrayAdapter adapter2;
    Button b5;
    EditText term,cname,sumass,policyNumber,age,commence;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.first_layout,container,false);
        return myview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Policy");
        term=(EditText)getActivity().findViewById(R.id.term);
        cname=(EditText)getActivity().findViewById(R.id.cname);
        sumass=(EditText)getActivity().findViewById(R.id.sumass);
        policyNumber=(EditText)getActivity().findViewById(R.id.policyNumber);
        age=(EditText)getActivity().findViewById(R.id.age);
        commence=(EditText)getActivity().findViewById(R.id.commence);
        b5=(Button)getActivity().findViewById(R.id.button5);

        String[] array;  //Use must initialize
        spinner2=(Spinner)getActivity().findViewById(R.id.plansub);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(
        getActivity(), R.array.health_plans, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter3);
        spinner = (Spinner) getActivity().findViewById(R.id.plan);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.main_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int ip = spinner.getSelectedItemPosition();
                spinner2.setVisibility(View.VISIBLE);
                if(ip==0){
                     adapter2= ArrayAdapter.createFromResource(
                            getActivity(), R.array.health_plans, android.R.layout.simple_spinner_item);
                }
                if(ip==1){
                    adapter2 = ArrayAdapter.createFromResource(
                            getActivity(), R.array.endowment_plans, android.R.layout.simple_spinner_item);
                }
                if(ip==2){
                    adapter2 = ArrayAdapter.createFromResource(
                            getActivity(), R.array.term_plans, android.R.layout.simple_spinner_item);
                }
                if(ip==3){
                    adapter2 = ArrayAdapter.createFromResource(
                            getActivity(), R.array.pension_plans, android.R.layout.simple_spinner_item);
                }
                if(ip==4){
                    adapter2 = ArrayAdapter.createFromResource(
                            getActivity(), R.array.life_Plan, android.R.layout.simple_spinner_item);
                }
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String planval=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String termval = term.getText().toString();
                    String cnameval = cname.getText().toString();
                    String ageval = age.getText().toString();
                    String policyNumberval = policyNumber.getText().toString();
                    String sumassval = sumass.getText().toString();
                    String commence2 = commence.getText().toString();
                if(policyNumber.length()==8 && Integer.parseInt(sumassval)>5000 && Integer.parseInt(sumassval)<1500000 && Integer.parseInt(ageval)>20 && Integer.parseInt(ageval)<60 && Integer.parseInt(termval)>2 && Integer.parseInt(termval)<20) {
                    dbr = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference childref = dbr.child("policies");
                    childref.child(policyNumberval).child("Name").setValue(cnameval);
                    childref.child(policyNumberval).child("age").setValue(ageval);
                    childref.child(policyNumberval).child("plan").setValue(planval);
                    childref.child(policyNumberval).child("term").setValue(termval);
                    childref.child(policyNumberval).child("sumAssured").setValue(sumassval);
                    childref.child(policyNumberval).child("commencementDate").setValue(commence2);
                    Toast.makeText(getActivity(), "Data send successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(),"Invalid data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
