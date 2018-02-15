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

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText emailFiled, passwordField;
    private TextInputLayout emailFieldLayout, passwordFieldLayout;
    private Button signinBtn;
    private TextView signupText;
    private String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //initialize the views
        init();
    }

    //method to initialize all views
    private void init() {
        emailFiled = (TextInputEditText) findViewById(R.id.input_email);
        emailFieldLayout = (TextInputLayout) findViewById(R.id.emailLayout);
        passwordField = (TextInputEditText) findViewById(R.id.input_password);
        passwordFieldLayout = (TextInputLayout) findViewById(R.id.passwordLayout);
        signinBtn = (Button) findViewById(R.id.signinBtn);
        signinBtn.setOnClickListener(this);
        signupText = (TextView) findViewById(R.id.signupText);
        signupText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.signinBtn:
                if(validate()) {

                    DBhelper db = new DBhelper(SigninActivity.this);
                    int result = db.loginDetails(email,password);
                    if(result == 1) {
                        Toast.makeText(this, "Login Successfully!!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }
                    else if (result == 2){
                        Toast.makeText(this, "Email is incorrect", Toast.LENGTH_SHORT).show();
                        emailFiled.setText(""); //empty email field
                        emailFiled.requestFocus();
                    }
                    else if(result == 0) {
                        Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                        passwordField.setText("");//empty password field
                        passwordField.requestFocus();
                    }
                }
                break;

            case R.id.signupText:
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
                break;
        }
    }


    //method to validate input received from user
    private boolean validate() {
        boolean flag = true; //set flag true
        //get user input fom edittext
        email = emailFiled.getText().toString();
        password = passwordField.getText().toString();

        if(email == null || email.isEmpty()) {
            flag = false; //set flag false
            emailFieldLayout.setError("Please enter an Email");
            emailFiled.requestFocus();
        }
        else if(password == null || password.isEmpty()){
            flag = false;
            emailFieldLayout.setError(null); //remove the error of email if caught earlier
            passwordFieldLayout.setError("Please enter a Password");
            passwordField.requestFocus();
        }
        return flag;
    }

}
