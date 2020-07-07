package com.example.firebase_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.FederatedSignInActivity;

import java.net.Inet4Address;

public class SignInActivity extends AppCompatActivity {

    private EditText mEmail_editTExt;
    private EditText mPassword_editTExt;

    private Button mSignIn_btn;
    private Button mBack_btn;
    private Button mRegister_btn;

    private ProgressBar mProgress_bar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance ();

        mEmail_editTExt= (EditText) findViewById (R.id.email_editText);
        mPassword_editTExt= (EditText) findViewById (R.id.password_editText);

        mSignIn_btn= (Button) findViewById (R.id.signIn_btn);
        mRegister_btn= (Button) findViewById (R.id.register_btn);
        mBack_btn= (Button) findViewById (R.id.back_btn);

        mProgress_bar= (ProgressBar) findViewById (R.id.loading_progressBar);

        mSignIn_btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(isEmpty ()) return;
                inProgress (true);
                mAuth.signInWithEmailAndPassword (mEmail_editTExt.getText ().toString (),
                        mPassword_editTExt.getText ().toString ())
                        .addOnSuccessListener (new OnSuccessListener<AuthResult> () {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this, "user signed in",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent( SignInActivity.this, MainActivity.class);
                                intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity (intent);
                                finish ();return;
                            }
                        }).addOnFailureListener (new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress (false);
                        Toast.makeText(SignInActivity.this, "sign in failed",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        mRegister_btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(isEmpty ()) return;
                inProgress (true);
                mAuth.createUserWithEmailAndPassword (mEmail_editTExt.getText ().toString (),
                        mPassword_editTExt.getText ().toString ())
                        .addOnSuccessListener (new OnSuccessListener<AuthResult> () {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this, "user registered ",Toast.LENGTH_LONG).show();
                                inProgress (false);
                            }
                        }).addOnFailureListener (new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress (false);
                        Toast.makeText(SignInActivity.this, "register failed",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        mBack_btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();return;
            }
        });
    }

    private void inProgress (boolean x ){
            if(x) {
                mProgress_bar.setVisibility (View.VISIBLE);
                mBack_btn.setEnabled (false);
                mSignIn_btn.setEnabled (false);
                mRegister_btn.setEnabled (false);
            }else{
                mProgress_bar.setVisibility (View.GONE);
                mBack_btn.setEnabled (true);
                mSignIn_btn.setEnabled (true);
                mRegister_btn.setEnabled (true);


            }
}

      private boolean isEmpty(){
        if(TextUtils.isEmpty (mEmail_editTExt.getText ().toString ())){
            mEmail_editTExt.setError ("REQUIRED");
            return true;
        }
          if(TextUtils.isEmpty (mPassword_editTExt.getText ().toString ())){
              mPassword_editTExt.setError ("REQUIRED");
              return true;
          }
        return false;
      }
}