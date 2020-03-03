package com.example.twitter_clone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLogIn extends AppCompatActivity implements View.OnClickListener{

    private EditText setSignUpUserName,setPassSignUp,logInUserName,logInPass;
    private Button logInBtn,signUpBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_log_in);

        setSignUpUserName=findViewById(R.id.userNameSignUp);
        setPassSignUp = findViewById(R.id.passwordSignUp);
        logInPass = findViewById(R.id.enterPassLogIn);
        logInUserName = findViewById(R.id.userNameLogIn);

        logInBtn = findViewById(R.id.logInBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        logInBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.logInBtn:
    ParseUser.logInInBackground(logInUserName.getText().toString(),
            logInPass.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null && user!=null){
                        Toast.makeText(SignUpLogIn.this, "Logged In "+
                                user.getUsername(), Toast.LENGTH_SHORT).show();

                    }

                    else{
                        Toast.makeText(SignUpLogIn.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }
            });





                break;

            case R.id.signUpBtn:

    final ParseUser appUser = new ParseUser();
    appUser.setUsername(setSignUpUserName.getText().toString());
    appUser.setPassword(setPassSignUp.getText().toString());
    appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            Toast.makeText(SignUpLogIn.this, "Logged In "+appUser.getUsername(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });



                break;
        }
    }
}
