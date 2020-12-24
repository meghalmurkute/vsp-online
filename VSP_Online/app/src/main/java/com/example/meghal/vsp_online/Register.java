package com.example.meghal.vsp_online;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.meghal.vsp_online.R.id.progressBar;


public class Register extends Activity
{

    AutoCompleteTextView emailid;
    EditText pass;
    Button email_registr_button, forgetpass, login;
    ProgressBar pbbar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        pbbar = (ProgressBar) findViewById(progressBar);
        pbbar.setVisibility(View.GONE);

        // Get Refferences of Views

        pass= (EditText)findViewById(R.id.password);
        emailid=(AutoCompleteTextView)findViewById(R.id.email);
        forgetpass=(Button)findViewById(R.id.btn_reset_password);
        forgetpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)

            {
                startActivity(new Intent(Register.this, ResetPasswordActivity.class));
            }
        });
        login=(Button)findViewById(R.id.sign_in_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
             }
        });
        email_registr_button=(Button)findViewById(R.id.email_registr_button);
        email_registr_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String email = emailid.getText().toString().trim();
                    String password = pass.getText().toString().trim();
                    if (TextUtils.isEmpty(email)) {
                       Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                       return;
                        }
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                        }
                    if (password.length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                        return;
                        }

                    pbbar.setVisibility(View.VISIBLE);
                    //create user
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                       @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            pbbar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                                } else {
                                startActivity(new Intent(Register.this, MainActivity.class));
                                finish();
                                }
                            }
                        });
                }

        });
    }

    @Override
    protected void onResume() {
    super.onResume();
    pbbar.setVisibility(View.GONE);
    }
}
