package com.example.or.contactlistmiddleproject;

import android.content.Intent;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Or on 11/08/2016.
 */
public class ContactInfo extends AppCompatActivity {

    TextView fullName;
    TextView tel_num;
    TextView address;
    TextView email;
    TextView birth_day;
    ImageView pic;
    Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);



        fullName = (TextView) findViewById(R.id.info_full_nmae);

        tel_num = (TextView)findViewById(R.id.info_tel_number);

        ImageView tel_btn = (ImageView)findViewById(R.id.info_tel_number_btn);
        tel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = tel_num.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phone_number));
                startActivity(intent);
            }
        });

        ImageView sms_btn =(ImageView)findViewById(R.id.info_massege_btn);
        sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = tel_num.getText().toString();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number,null)));
            }
        });



        address = (TextView)findViewById(R.id.info_address);
        final String addressUri = address.getText().toString();
        ImageView map_btn = (ImageView)findViewById(R.id.info_map_btn);
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Uri uri = Uri.parse("geo:0,0?q="+Uri.encode(ContactInfo.this.person.getAdrress()));
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
            }
        });

        email = (TextView)findViewById(R.id.info_email);
        ImageView email_btn = (ImageView)findViewById(R.id.info_email_btn);
        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = email.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
                intent.putExtra(Intent.EXTRA_SUBJECT, "This is the email subject");
                intent.putExtra(Intent.EXTRA_TEXT, "This is the email body");
                intent.setType("text/html");
                startActivity(intent);


            }
        });

        birth_day = (TextView)findViewById(R.id.info_birthday);

        pic = (ImageView)findViewById(R.id.info_pic);

        Intent intent = getIntent();
        person = (Person)intent.getExtras().getSerializable("person");
        pressentPerson(person);



    }
    private void pressentPerson(Person person){
        if (person.getFullName()!=null){
            fullName.setText(person.getFullName());
        }
        if (person.getTelNum()!=null){
            tel_num.setText(person.getTelNum());
        }
        if (person.getAdrress()!=null){
            address.setText(person.getAdrress());
        }
        if (person.getEmail()!=null){
            email.setText(person.getEmail());
        }
        if (person.getBirth_day()!=null){
            birth_day.setText(person.getBirth_day());
        }

        if(person.getPic()!=null){
            pic.setImageBitmap(person.getPic());
        }
    }
}
