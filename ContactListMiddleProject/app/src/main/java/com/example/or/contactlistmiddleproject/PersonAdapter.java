package com.example.or.contactlistmiddleproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Or on 14/08/2016.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> personList;
    Context context;

    public PersonAdapter(ViewContact context ,List <Person> personList ) {
        this.context = context;
        this.personList = personList;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView;


        public PersonViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.person_pic);
            nameTextView = (TextView)itemView.findViewById(R.id.person_name);

        }
    }

    @Override
    public void onBindViewHolder(PersonAdapter.PersonViewHolder holder, final int position) {
        holder.nameTextView.setText(personList.get(position).getFullName()+"");
        holder.imageView.setImageBitmap(personList.get(position).getPic());
        holder.itemView.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactInfo.class);
                intent.putExtra("person", personList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lay_out,parent ,false);
        PersonViewHolder personViewHolder = new PersonViewHolder(v);
        return personViewHolder;
    }


    @Override
    public int getItemCount() {
        return personList.size();
    }


}
