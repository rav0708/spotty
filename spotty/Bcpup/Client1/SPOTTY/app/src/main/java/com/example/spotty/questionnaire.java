package com.example.spotty;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.jetbrains.annotations.NotNull;


//This is the interface which showcase the questionnaire for the user to provide their user preferences
public class questionnaire extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RadioButton maleRadioBtn, femaleRadioBtn, otherRadioBtn, warmBtn, coolBtn, neutralBtn;
    RadioGroup radioGroup;
    Button submit,connect,comment,skip;
    String selectedGender, selectedTone;

    private EditText editText, colour;
    private OkHttpClient okHttpClient;

    private static final String TAG = "C_details";

    private String postBodyString;
    private RequestBody requestBody;



    /*String dummyText1 = selectedGender;
    String dummyText2= selectedTone;
    String dummyText3;
    String dummyText4;
    String dummyText5=colour.getText().toString();

    String send= ""+dummyText1+","+dummyText2+","+dummyText3+","+dummyText4+","+dummyText5+"";*/

    /*MediaType JSON = MediaType.parse("application/json; charset=utf-8");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        getSupportActionBar().hide(); //to hide the action bar - name of the app

        //Button submit = findViewById(R.id.submit);
        Button connect = findViewById(R.id.connect);
        maleRadioBtn = (RadioButton) findViewById(R.id.MaleradioButton);
        femaleRadioBtn = (RadioButton) findViewById(R.id.FemaleradioButton);
        otherRadioBtn = (RadioButton) findViewById(R.id.OtherradioButton);

        warmBtn = (RadioButton) findViewById(R.id.warm);
        coolBtn = (RadioButton) findViewById(R.id.cool);
        neutralBtn = (RadioButton) findViewById(R.id.neutral);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        colour = findViewById(R.id.Q1);
        skip=findViewById(R.id.skipnxt);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu = new Intent(questionnaire.this, menu.class);
                startActivity(Menu);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);




        okHttpClient = new OkHttpClient();




        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(questionnaire.this,
                R.array.Building_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);





        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(questionnaire.this,
                R.array.Compartment_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter1);


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (maleRadioBtn.isChecked()) {
                    selectedGender = maleRadioBtn.getText().toString();
                } else if (femaleRadioBtn.isChecked()) {
                    selectedGender = femaleRadioBtn.getText().toString();
                } else if (otherRadioBtn.isChecked()) {
                    selectedGender = otherRadioBtn.getText().toString();

                }
                Toast.makeText(getApplicationContext(), selectedGender, Toast.LENGTH_SHORT).show(); // print the value of selected gender

                if (warmBtn.isChecked()) {
                    selectedTone = warmBtn.getText().toString();
                } else if (coolBtn.isChecked()) {
                    selectedTone = coolBtn.getText().toString();
                } else if (neutralBtn.isChecked()) {
                    selectedTone = neutralBtn.getText().toString();

                }
                Toast.makeText(getApplicationContext(), selectedTone, Toast.LENGTH_SHORT).show(); // print the value of selected tone




                Log.d(TAG, "colour: " + colour.toString());


                String Colour = colour.getText().toString().trim();


                if (Colour.isEmpty()) {
                    colour.setError("Basic colour theme is required!");
                }

                else{
                    Toast.makeText(getApplicationContext(), Colour, Toast.LENGTH_SHORT).show();
                    Intent menu = new Intent(questionnaire.this, menu.class);
                    startActivity(menu);

                }


                Toast.makeText(questionnaire.this,
                        "selected : " +
                                "\nBuilding type : "+ String.valueOf(spinner.getSelectedItem()) +
                                "\nCompartment : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                //List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                /*startActivity(new Intent(questionnaire.this, dummy.class));*/
                //String dummyText1 = editText.getText().toString();
                //String dummyText2 = editText.getText().toString();

                String dummyText1 = selectedGender;
                String dummyText2= selectedTone;
                String dummyText3= String.valueOf(spinner.getSelectedItem());
                String dummyText4=String.valueOf(spinner2.getSelectedItem());
                String dummyText5=colour.getText().toString();

                String send= ""+dummyText1+"\n"+dummyText2+"\n"+dummyText3+"\n"+dummyText4+"\n"+dummyText5+"";
                /*String dummyText3 = editText.getText().toString();
*/
                Intent intent = new Intent(questionnaire.this,menu.class );
                intent.putExtra("send", send);
                startActivity(intent);


               /* MediaType JSON = MediaType.parse("application/json; charset=utf-8");*/

                //boolean dummy1=nameValuePair.add(new BasicNameValuePair("colour", dummyText1));
                //boolean dummy2=nameValuePair.add(new BasicNameValuePair("hi", dummyText2));


                // we add the information we want to send in
                // a form. each string we want to send should
                // have a name. in our case we sent the
                // dummyText with a name 'sample'




               /*RequestBody formbody
                        = new FormBody.Builder()
                        .add("test", send)
                        .build();


                Request request = new Request.Builder().url("http://192.168.1.2:5000/")
                        .post(formbody)
                        .build();
*/



               /* RequestBody test = RequestBody.create(JSON, send);*/

              /*  Request request = new Request.Builder()
                        .url("http://192.168.1.4:5000/")
                        .post(test)
                        .build();
*/



                // while building request
                // we give our form
                // as a parameter to post()

               /* Request request = new Request.Builder().url("http://192.168.1.3:5000/")
                        .post(formbody)
                        .build();*/

               /*okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(
                            @NotNull Call call,
                            @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("received")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "data received", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });*/


            }
        });



        //intialization of the submit btn

  /*      submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (maleRadioBtn.isChecked()) {
                    selectedGender = maleRadioBtn.getText().toString();
                } else if (femaleRadioBtn.isChecked()) {
                    selectedGender = femaleRadioBtn.getText().toString();
                } else if (otherRadioBtn.isChecked()) {
                    selectedGender = otherRadioBtn.getText().toString();

                }
                Toast.makeText(getApplicationContext(), selectedGender, Toast.LENGTH_SHORT).show(); // print the value of selected gender

                if (warmBtn.isChecked()) {
                    selectedTone = warmBtn.getText().toString();
                } else if (coolBtn.isChecked()) {
                    selectedTone = coolBtn.getText().toString();
                } else if (neutralBtn.isChecked()) {
                    selectedTone = neutralBtn.getText().toString();

                }
                Toast.makeText(getApplicationContext(), selectedTone, Toast.LENGTH_SHORT).show(); // print the value of selected tone




                Log.d(TAG, "colour: " + colour.toString());


                String Colour = colour.getText().toString().trim();


                if (Colour.isEmpty()) {
                    colour.setError("Basic colour theme is required!");
                }

                else{
                    Intent menu = new Intent(questionnaire.this, menu.class);
                    startActivity(menu);
                }


                Toast.makeText(questionnaire.this,
                        "selected : " +
                                "\nBuilding type : "+ String.valueOf(spinner.getSelectedItem()) +
                                "\nCompartment : "+ String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();

            }


            *//*  System.out.println(Colour);*//*

            *//*colour_print();*//*



                *//*Intent menu = new Intent(questionnaire.this, menu.class);
                startActivity(menu);*//*
            //finish();






        });*/


    }


   /* private void colour_print() {


        Log.d(TAG, "colour: " + colour.toString());


        String Colour = colour.getText().toString().trim();


        if (Colour.isEmpty()) {
            colour.setError("Basic colour theme is required!");
        }

        System.out.println(Colour);
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback



    }



}

