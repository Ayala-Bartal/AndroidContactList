package com.example.or.contactlistmiddleproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView addContact = (ImageView) findViewById(R.id.add_contact_btn);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddContact.class);
                    startActivity(intent);
            }
        });

        ImageView viewContact = (ImageView) findViewById(R.id.contact_list_btn);
        viewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewContact.class);
                startActivity(intent);
            }
        });
    }
}
