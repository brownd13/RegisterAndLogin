/* Daniel Brown
 * M2 - Registration and Login
 * 2023-02-12
 *
 * Changes - 2023-02-15
 *      Moved some remaining strings (Toast text) to strings.xml
 *      Completed splash screen
 *      Added First Name length check
 *      TODO - return Intent/bundle from a closed activity rather than starting new activity
 */
package com.example.registerandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class activityLogin
        extends AppCompatActivity
        implements View.OnClickListener {

    private Button buLogin;
    private Button buRegister;
    private EditText etUserID;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
            Toast.makeText(this, R.string.toLoginSuccess, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toLoginFail, Toast.LENGTH_SHORT).show();
        }

    }
}