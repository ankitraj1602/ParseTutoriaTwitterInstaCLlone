package com.example.twitter_clone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName;
    private  EditText txtPunchSpeed;
    private EditText txtPunchPower;
    private EditText txtKickSpeed;
    private EditText txtKickPower;
    private Button saveDataBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        txtName = findViewById(R.id.nameTxt);
        txtKickPower =findViewById(R.id.kickPowerTxt);
        txtKickSpeed = findViewById(R.id.kickSpeedText);
        txtPunchPower = findViewById(R.id.punchPwerTxt);
        txtPunchSpeed = findViewById(R.id.punchSpeedTxt);
        saveDataBtn = findViewById(R.id.button);

        saveDataBtn.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        try{
        int punchSpeed = Integer.parseInt(txtPunchSpeed.getText().toString());
       int kickSpeed = Integer.parseInt(txtKickSpeed.getText().toString());
       int kickPower = Integer.parseInt(txtKickPower.getText().toString());
       int punchPower = Integer.parseInt(txtPunchPower.getText().toString());


        final ParseObject boxer2 = new ParseObject("Boxer");
        boxer2.put("Name",txtName.getText().toString());
        boxer2.put("PunchSpeed",punchSpeed);
        boxer2.put("PunchPower",punchPower);
        boxer2.put("KickSpeed",kickSpeed);
        boxer2.put("KickPower",kickPower);

        boxer2.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){

    FancyToast.makeText(MainActivity.this,boxer2.get("Name")+" Uploaded to server", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();


                }

     else{
                    FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
            }
        });




    } catch (Exception e){
           FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();



       }}
}