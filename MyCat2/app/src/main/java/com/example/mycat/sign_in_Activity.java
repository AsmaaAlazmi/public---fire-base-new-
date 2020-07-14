package com.example.mycat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class sign_in_Activity extends AppCompatActivity {

    private Button register;
    private Button login;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button reset;
    private TextView textView;
    private View root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_in_);

        //// loading

        final LoadingDialog loadingDialog = new LoadingDialog (sign_in_Activity.this);

        ///////////////////////////



        ///////////////////////////////////////////
        reset = findViewById(R.id.reset_btn);
        editTextUsername = findViewById(R.id.UserName_EditText);
        editTextPassword = findViewById(R.id.Password_EditText);
        editTextEmail = findViewById(R.id.Email_EditText);
        register = findViewById(R.id.register_btn);
        login = findViewById(R.id.sign_in_btn);

        editTextUsername.addTextChangedListener (loginTextWatcher);
        editTextPassword.addTextChangedListener(loginTextWatcher);
        editTextEmail.addTextChangedListener(loginTextWatcher);



/////////////////////////////////////////////////////////////////////////////////////
        login.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText(sign_in_Activity.this, "الحساب غير موجود , حاول مجددد او انشا حساب جديد",Toast.LENGTH_SHORT).show();

            }
        });

        register.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( sign_in_Activity.this, selection_A.class);

                i.putExtra("username",editTextUsername.getText().toString());

                     // loading
                loadingDialog.StartLoadingDialog ();
                Handler handler = new Handler ();
                handler.postDelayed (new Runnable () {
                    @Override
                    public void run() {
                        loadingDialog.dismisDialog ();

                    }
                }, 9000
                );
                     ////

                startActivity (i);
            }
        });
  ///////////////////reset button :
        reset.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                editTextUsername.setText ("");
                editTextEmail.setText ("");
                editTextPassword.setText ("");

            }
        });
   ////////////////////////////////////////////


    }

    /////////////editText visible
    private TextWatcher loginTextWatcher = new TextWatcher () {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = editTextUsername.getText().toString().trim();
            String passwordInput = editTextPassword.getText().toString().trim();
            String emailInput = editTextEmail.getText().toString().trim();
            register.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty()&& !emailInput.isEmpty());
            login.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty()&& !emailInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}