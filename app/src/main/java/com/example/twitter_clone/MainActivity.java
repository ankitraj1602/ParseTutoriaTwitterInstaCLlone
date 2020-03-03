package com.example.twitter_clone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName;
    private  EditText txtPunchSpeed;
    private EditText txtPunchPower;
    private EditText txtKickSpeed;
    private EditText txtKickPower;
    private Button saveDataBtn;

    private Button btnTransition;

    private TextView txtDataServer;

    private String allData = "";

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

        btnTransition = findViewById(R.id.btnTransition);
        saveDataBtn.setOnClickListener(this);

        txtDataServer = findViewById(R.id.txtDataServer);
        txtDataServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

       ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Boxer");
       parseQuery.findInBackground(new FindCallback<ParseObject>() {
           @Override
           public void done(List<ParseObject> objects, ParseException e) {
               if(e==null){
            if(objects.size()>0){
           for(ParseObject object :objects){

               allData = allData + object.get("Name") + "\n";



           }}

  FancyToast.makeText(MainActivity.this,allData, FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
               }

               else{
 FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

               }


           }
       });




       //       parseQuery.getInBackground("xTfB8udAOo",
//               new GetCallback<ParseObject>() {
//       @Override
//       public void done(ParseObject object, ParseException e) {
//           if(object!=null && e==null){
//
//               txtDataServer.setText(object.get("Name")+"");
//
//           }
//
//
//
//                    }
//                });


            }
        });

     btnTransition.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intentToGoToSignUpLogIn = new Intent
                     (MainActivity.this,SignUpLogIn.class);
             startActivity(intentToGoToSignUpLogIn);
         }
     });
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
