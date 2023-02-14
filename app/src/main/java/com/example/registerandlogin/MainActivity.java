/* Daniel Brown
 * M2 - Registration and Login
 * 2023-02-12
 */
package com.example.registerandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    private Button buLogin;
    private Button buRegister;
    private EditText etUserID;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setup button click listeners
        buLogin = findViewById(R.id.login);
        buRegister = findViewById(R.id.register);
        buLogin.setOnClickListener(this);
        buRegister.setOnClickListener(this);
    }
    // OnClick method for login and register buttons
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.login:
                loginUser(view);
                break;
            case R.id.register:
                launchRegisterAct(view);
                break;
        }
    }
    // Launch Register activity. Intent stores the username if it has been
    // provided on the login screen.
    public void launchRegisterAct(View view){
        Intent intent = new Intent(this, activity_register.class);
        etUserID = findViewById(R.id.userID);
        String strLogin = etUserID.getText().toString();
        Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        intent.putExtra("EXTRA_LOGIN_KEY", strLogin);
        startActivity(intent);
    }
    public void loginUser(View view){
        etUserID = findViewById(R.id.userID);
        etPassword = findViewById(R.id.password);
        // Retrieve Intent from the register activity to compare stored userId and password
        Intent intent = getIntent();
        String strLogin = intent.getStringExtra("EXTRA_LOGIN_KEY");
        String strPasswd = intent.getStringExtra("EXTRA_PASSWD_KEY");

        // Check for string equivalence. Password is just in plain text for this example.
        if (Objects.equals(strLogin, etUserID.getText().toString()) &&
                Objects.equals(strPasswd, etPassword.getText().toString())) {
            Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }

    }
}