package com.example.mycat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter {

    ArrayList<Cats> pArray;
    Context c;

    public adapter(ArrayList<Cats> pArray, Context c) {
        this.pArray = pArray;
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.activity_lost_cats__details,parent,false);
        ViewHolder vh = new ViewHolder (v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


        ((ViewHolder)holder).BlockandStreet.setText (pArray.get (position).getStreetAndBlock ()+"");
        ((ViewHolder)holder).Area.setText (pArray.get (position).getArea ()+"");
        ((ViewHolder)holder).Details_cat.setText (pArray.get (position).getDetails ()+"");

        ((ViewHolder)holder).view.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (c,LostCats_inside.class);

                i.putExtra("Cats",pArray.get (position));

                c.startActivity (i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return pArray.size ();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Area ;
        public TextView BlockandStreet ;
        public View view ;
        public TextView Details_cat;


        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            view = itemView;

            Details_cat = itemView.findViewById(R.id.details_textView_details);
            Area = itemView.findViewById(R.id.area_textView_details);
            BlockandStreet = itemView.findViewById(R.id.street_textView_details);


        }
    }
}

