package com.example.or.contactlistmiddleproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Or on 09/08/2016.
 */
public class AddContact extends AppCompatActivity {

    final int CAMERA_REQUEST = 1;
    Bitmap bitmap;
    ArrayList<Person> personList = new ArrayList<Person>();
    EditText fullName;
    EditText tel_number;
    EditText address;
    EditText email;
    TextView hour;
    TextView day;
    TextView date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        fullName = (EditText) findViewById(R.id.add_full_nmae);
        tel_number = (EditText) findViewById(R.id.add_tel_number);
        address = (EditText) findViewById(R.id.add_address);
        email = (EditText) findViewById(R.id.add_email);


        ImageView birth_day = (ImageView) findViewById(R.id.add_date_btn);
        birth_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar current = Calendar.getInstance();
                int year = current.get(Calendar.YEAR);
                int month = current.get(Calendar.MONTH);
                int day = current.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(AddContact.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date = (TextView) findViewById(R.id.add_birthday);
                        int a = i1+1;
                        date.setText(i2 + "/" + a + "/" + i);
                    }
                }, year, month, day);
                dpd.show();

            }
        });





        ImageView pic = (ImageView) findViewById(R.id.add_camera_btn);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);

            }
        });

            try{
            FileInputStream fis = openFileInput("person.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            personList = (ArrayList<Person>) ois.readObject();
            ois.close();
            if(personList == null) {
                personList = new ArrayList<Person>();
            }


        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (StreamCorruptedException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){
            ImageView pic;
            pic =(ImageView)findViewById(R.id.add_pic);
            bitmap = (Bitmap)data.getExtras().get("data");
            pic.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.first_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.back) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id==R.id.save){

            AlertDialog.Builder builder = new AlertDialog.Builder(AddContact.this);
            builder.setTitle("Are you sure?");
            builder.setPositiveButton("Yes", new MyDialogListener());
            builder.setNegativeButton("No", new MyDialogListener());
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();

        }

        return super.onOptionsItemSelected(item);
    }

    private class MyDialogListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE){
                Person person = new Person();
                EditText fullName = (EditText)findViewById(R.id.add_full_nmae);
                person.setFullName(fullName.getText().toString());
                EditText telNum = (EditText)findViewById(R.id.add_tel_number);
                person.setTelNum(telNum.getText().toString());
                EditText address = (EditText)findViewById(R.id.add_address);
                person.setAdrress(address.getText().toString());
                EditText email = (EditText)findViewById(R.id.add_email);
                person.setEmail(email.getText().toString());
                TextView date = (TextView)findViewById(R.id.add_birthday);
                person.setBirth_day(date.getText().toString());
                person.setPic(bitmap);
                personList.add(person);
                try{
                    FileOutputStream fos = openFileOutput("person.dat", Activity.MODE_PRIVATE);
                    ObjectOutputStream oos= new ObjectOutputStream(fos);
                    oos.writeObject(personList);
                    oos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(AddContact.this, personList.size()+"", Toast.LENGTH_SHORT).show();


            }
            else if (which == DialogInterface.BUTTON_NEGATIVE){
                Toast.makeText(AddContact.this, "Contact not saved!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



