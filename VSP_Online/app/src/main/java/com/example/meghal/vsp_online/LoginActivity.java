package com.example.meghal.vsp_online;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

import static com.google.android.gms.internal.zzt.TAG;

public class LoginActivity extends Activity {

    private AutoCompleteTextView editTextUserName;
    private EditText editTextPassword;
    private Button email_signin;
    private Button email_register;
    private Button forget;
    ProgressBar pbbar;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                 if (user != null) {
                     // User is signed in
                     Intent intent = new Intent(LoginActivity.this, Shop.class);
                     startActivity(intent);
                     finish();
                     Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                     } else {
                     // User is signed out
                     Log.d(TAG, "onAuthStateChanged:signed_out");
                     }
            }
        };
        pbbar = (ProgressBar) findViewById(R.id.progressBar);
        pbbar.setVisibility(View.GONE);
        editTextUserName = (AutoCompleteTextView) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        email_register = (Button) findViewById(R.id.email_registr_button);
        email_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this, Register.class));
            }
            });
        forget=(Button) findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                }
            });

        email_signin = (Button) findViewById(R.id.email_sign_in_button);
        email_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextUserName.getText().toString();
                final String password = editTextPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                    }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                    }
               pbbar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pbbar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                         // there was an error
                            if (password.length() < 6) {
                                editTextPassword.setError(getString(R.string.minimum_password));
                                } else {
                               Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                            } else {
                           Intent intent = new Intent(LoginActivity.this, Shop.class);
                   startActivity(intent);
                   finish();
                   }
                   }
                   });
                }
            });

    }
    @Override
    public void onStart() {
         super.onStart();
         auth.addAuthStateListener(mAuthListener);
         // ...
    }

    @Override
    public void onStop() {
         super.onStop();
         if (mAuthListener != null) {
         auth.removeAuthStateListener(mAuthListener);
         }
         // ...
    }

}

