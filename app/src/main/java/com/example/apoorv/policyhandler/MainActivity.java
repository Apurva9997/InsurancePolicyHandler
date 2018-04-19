package com.example.apoorv.policyhandler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    Button b1;TextView tv;
    FirebaseAuth fauth;
    FirebaseUser mFirebaseUser;
    //ProgressBar progressBar;
    FirebaseAuth.AuthStateListener authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        tv=(TextView)findViewById(R.id.textView);
        b1=(Button)findViewById(R.id.button2);
        SharedPreferences spref=getSharedPreferences("sp12" , Context.MODE_PRIVATE);
        String tempUname=spref.getString("username",null);
        String tempPass=spref.getString("password",null);
        System.out.println(tempUname+"\\\\\\\\t**********"+tempPass);
        if(!(tempUname==null) && !(tempPass==null)) {
            ed1.setText(tempUname);
            ed2.setText(tempPass);
        }
        //progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        fauth=FirebaseAuth.getInstance();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(in);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = ed1.getText().toString();
                final String password = ed2.getText().toString();

                SharedPreferences spref=getSharedPreferences("sp12" , Context.MODE_PRIVATE);
                spref.edit().putString("username",email).commit();
                spref.edit().putString("password",password).commit();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //progressBar.setVisibility(View.VISIBLE);
                    fauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                // there was an error
                                if (password.length() < 6) {
                                    Toast.makeText(getBaseContext(), getString(R.string.minimum_password), Toast.LENGTH_LONG).show();
                                    return;
                                } else {
                                    Toast.makeText(getBaseContext(), getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } else {
                                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });
            }
        });
    }
}
