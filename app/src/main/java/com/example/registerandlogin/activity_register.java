package com.example.registerandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_register
        extends AppCompatActivity {
    // EditText instances for all text fields
    private EditText etUserID;
    private EditText etFirstName;
    private EditText etFamName;
    private EditText etDOB;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Pull User ID from main activity if one was entered
        Intent intent = getIntent();
        String strLogin = intent.getStringExtra("EXTRA_LOGIN_KEY");
        etUserID = findViewById(R.id.userID);
        etUserID.setText(strLogin);
    }

    public void registerNewUser(View view){
        // Setup EditText instance
        etUserID = findViewById(R.id.userID);
        etFirstName = findViewById(R.id.firstName);
        etFamName = findViewById(R.id.familyName);
        etDOB = findViewById(R.id.DOB);
        etEmail= findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

        boolean inputValid = true;  // Will be set to false if any input criteria not met

        // Check for empty fields
        if (etUserID.getText().toString().isEmpty()) inputValid = false;
        if (etFirstName.getText().toString().isEmpty()) inputValid = false;
        if (etFamName.getText().toString().isEmpty()) inputValid = false;
        if (etDOB.getText().toString().isEmpty()) inputValid = false;
        if (etEmail.getText().toString().isEmpty()) inputValid = false;
        if (etPassword.getText().toString().isEmpty()) inputValid = false;
        if (!inputValid){
            Toast.makeText(this, R.string.toMissingField, Toast.LENGTH_LONG).show();
        }
        // Check first name length
        if (etFirstName.getText().toString().length() < 3 ||
                etFirstName.getText().toString().length() > 30  ) {
            inputValid = false;
            Toast.makeText(this, R.string.toFirstNameLenBad, Toast.LENGTH_LONG).show();
        }
        // Validate email format
        if ( !Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches() ) {
            inputValid = false;
            Toast.makeText(this, R.string.toBadEmail, Toast.LENGTH_LONG).show();
        }
        // Validate Date of Birth format MM/DD/YYYY
        if ( !etDOB.getText().toString().matches(getString(R.string.format_MMDDYYYY))) {
            inputValid = false;
            Toast.makeText(this, R.string.toBadDOB, Toast.LENGTH_LONG).show();
        }
        if (!inputValid) return; // Allow user to correct input fields

        // Store Id and password hash for login later
        Intent intent = new Intent(this, activityLogin.class);
        intent.putExtra("EXTRA_LOGIN_KEY", etUserID.getText().toString());
        intent.putExtra("EXTRA_PASSWD_KEY", etPassword.getText().toString());
        startActivity(intent);
    }
}