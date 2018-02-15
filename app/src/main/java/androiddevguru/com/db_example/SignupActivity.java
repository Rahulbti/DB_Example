package androiddevguru.com.db_example;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androiddevguru.com.db_example.DBHelper.DBhelper;
import androiddevguru.com.db_example.DataModel.Users;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText nameField, emailField, mobileField, passwordField;
    private TextInputLayout nameFieldLayout, emailFieldLayout, mobileFieldLayout, passwordFieldLayout;
    private Button signupBtn;
    private TextView signinText;
    private String name, email, mobile, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        init();
    }

    private void init() {
        nameField = (TextInputEditText) findViewById(R.id.input_name);
        nameFieldLayout = (TextInputLayout) findViewById(R.id.nameLayout);
        emailField = (TextInputEditText) findViewById(R.id.input_email);
        emailFieldLayout = (TextInputLayout) findViewById(R.id.emailLayout);
        mobileField = (TextInputEditText) findViewById(R.id.input_mobile);
        mobileFieldLayout = (TextInputLayout) findViewById(R.id.mobileLayout);
        passwordField = (TextInputEditText) findViewById(R.id.input_password);
        passwordFieldLayout = (TextInputLayout) findViewById(R.id.passwordLayout);
        signupBtn = (Button) findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(this);
        signinText = (TextView) findViewById(R.id.signinText);
        signinText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.signupBtn:
                if(validate()){

                    DBhelper db = new DBhelper(SignupActivity.this);
                    Users users = new Users();
                    users.setName(name);
                    users.setEmail(email);
                    users.setMobile(mobile);
                    users.setPassword(password);
                    boolean status = db.userRegistration(users);
                    if(status) {
                        Toast.makeText(this, "User Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }
                    else {
                        Toast.makeText(this, "User not Registered Successfully", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.signinText:
                finish();
                break;
        }
    }

    //method to validate user input
    private boolean validate() {
        boolean flag = true; //set flag true
        name = nameField.getText().toString();
        email = emailField.getText().toString();
        mobile = mobileField.getText().toString();
        password = passwordField.getText().toString();

        if(name == null || name.isEmpty()) {
            flag = false;//set the flaf value false
            nameFieldLayout.setError("Please enter your name");
            nameField.requestFocus();
        }
        else if(email == null || email.isEmpty()) {
            flag = false;
            emailFieldLayout.setError("Please enter an Email");
            emailField.requestFocus();
            nameFieldLayout.setError(null);
        }
        else if(mobile == null || mobile.isEmpty()) {
            flag = false;
            mobileFieldLayout.setError("Please enter Mobile no.");
            mobileField.requestFocus();
            emailFieldLayout.setError(null);
        }
        else if(password == null || password.isEmpty()) {
            flag = false;
            passwordFieldLayout.setError("Please choose a Password");
            passwordField.requestFocus();
            mobileFieldLayout.setError(null);
        }
        return flag;
    }
}
