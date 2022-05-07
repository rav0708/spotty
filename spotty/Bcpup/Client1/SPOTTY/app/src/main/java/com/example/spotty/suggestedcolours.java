package com.example.spotty;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

//This class is used  to develop the interface which visualize the suggested colour pallet to the user
public class suggestedcolours extends AppCompatActivity {

    private String postBodyString;
    private MediaType mediaType;
    private RequestBody requestBody;
    private Button connect;

    TextView pagenameTextView,pagenameTextView1,pagenameTextView2,pagenameTextView3, getPagenameTextView4 ;
    ImageView colorsnip;
    Button colorBTN,colorBTN1,colorBTN2,colorBTN3,colorBTN4, preview, pallet, menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestedcolours);
        getSupportActionBar().hide(); //to hide the action bar - name of the app

        pagenameTextView = findViewById(R.id.pagename);
        pagenameTextView1=findViewById(R.id.pagename1);
        pagenameTextView2 = findViewById(R.id.pagename2);
        pagenameTextView3=findViewById(R.id.pagename3);
//        getPagenameTextView4=findViewById(R.id.pagename4);


        colorBTN= (Button) findViewById(R.id.colorBTN);
        colorBTN1= (Button) findViewById(R.id.colorBTN1);
        colorBTN2= (Button) findViewById(R.id.colorBTN2);
        colorBTN3= (Button) findViewById(R.id.colorBTN3);
        preview=(Button)findViewById(R.id.preview);
        pallet=(Button)findViewById(R.id.pallet);
        menu=(Button)findViewById(R.id.menu1);

        String suggest1=getIntent().getStringExtra("suggest");

        String nn=suggest1;
        String arr[]=nn.split("  ");

           String a= ""+arr[0];
           String b= ""+arr[1];
           String c= ""+arr[2];
           String d= ""+arr[3];
//           String e= ""+arr[4];


        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
//        System.out.println(e);


        ////////////////////////////////////////////////////////
        String bh[]=a.split("#");

        String color_code1= "#"+bh[1];
        System.out.println(color_code1);

        ////////////////////////////////////////////////////////
        String bh1[]=b.split("#");

        String color_code2= "#"+bh1[1];
        System.out.println(color_code2);


        //////////////////////////////////////////////////////////
        String bh2[]=c.split("#");

        String color_code3= "#"+bh2[1];
        System.out.println(color_code3);

        //////////////////////////////////////////////////////////
        String bh3[]=d.split("#");

        String color_code4= "#"+bh3[1];
        System.out.println(color_code4);

        ////////////////////////////////////////////////////////////

//        String bh4[]=d.split("#");
//
//        String color_code5= "#"+bh4[1];
//        System.out.println(color_code5);
//




        pagenameTextView.setText(a);
         pagenameTextView1.setText(b);
         pagenameTextView2.setText(c);
         pagenameTextView3.setText(d);
//         pagenameTextView3.setText(e);





        int color = Color.parseColor(color_code1);
        colorBTN.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC));

        int color1=Color.parseColor(color_code2);
        colorBTN1.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(color1, PorterDuff.Mode.SRC));

        int color2=Color.parseColor(color_code3);
        colorBTN2.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(color2, PorterDuff.Mode.SRC));

        int color3=Color.parseColor(color_code4);
        colorBTN3.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(color3, PorterDuff.Mode.SRC));





        colorBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent preview=new Intent(suggestedcolours.this,samplepreview.class);
               startActivity(preview);
                String color_code1;
                // finish();


            }        });


        colorBTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preview1=new Intent(suggestedcolours.this,samplepreview.class);
                startActivity(preview1);
                String color_code2;
                // finish();


            }        });


        colorBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preview2=new Intent(suggestedcolours.this,samplepreview.class);
                startActivity(preview2);
                String color_code3;
                // finish();


            }        });


        colorBTN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preview3=new Intent(suggestedcolours.this,samplepreview.class);
                startActivity(preview3);
                String color_code4;
                // finish();


            }        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preview4=new Intent(suggestedcolours.this,samplepreview.class);
                startActivity(preview4);
                // finish();


            }        });


       // String sendColour= ""+color_code1+"\n"+color_code2+"\n"+color_code3+"\n"+color_code4+"";


        pallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pallet=new Intent(suggestedcolours.this,colour_pallet.class);
                startActivity(pallet);
                // finish();


            }        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu=new Intent(suggestedcolours.this,menu.class);
                startActivity(menu);
                // finish();


            }        });


    }


}