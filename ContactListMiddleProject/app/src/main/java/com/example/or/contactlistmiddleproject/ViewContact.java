package com.example.or.contactlistmiddleproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by Or on 11/08/2016.
 */
public class ViewContact extends AppCompatActivity {

    ArrayList<Person> personArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contact);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(llm);


        try{
            FileInputStream fis = openFileInput("person.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            personArrayList = (ArrayList<Person>)ois.readObject();
            ois.close();
            if (personArrayList != null){
                PersonAdapter adapter = new PersonAdapter(this, personArrayList);
                recyclerView.setAdapter(adapter);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.back_action){
            Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);
        }

        if (id ==R.id.add_action ){
            Intent intent = new Intent(this, AddContact.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}


