package com.example.apoorv.policyhandler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b3;
    FirebaseAuth fauth;
    FirebaseAuth.AuthStateListener authListener;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ed1=(EditText)findViewById(R.id.editText3);
        ed2=(EditText)findViewById(R.id.editText4);
        ed3=(EditText)findViewById(R.id.editText5);
        b3=(Button)findViewById(R.id.button3);
        fauth=FirebaseAuth.getInstance();
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed1.getText().toString();
                String password = ed2.getText().toString();
                String cpassword = ed3.getText().toString();
                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(cpassword)) {
                    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
                    if(!matcher.find()) {
                        Toast.makeText(getBaseContext(), "Email address is not valid",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!password.equals(cpassword)){
                        Toast.makeText(getBaseContext(), "Passwords do not match",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "User Added successfully",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), "Invalid input.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                            // ...
                });
                } else {
                    Toast.makeText(getBaseContext(), "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }
        }
    });
    }
}
