package com.example.mycat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class selection_A extends AppCompatActivity {

    public Button adopte;
    public Button lost;
    public Button charity;
    public TextView nameWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_selection_);

         adopte = findViewById (R.id.adopte_cat_btn);
         lost = findViewById (R.id.lost_cat_btn);
         charity = findViewById (R.id.charity_cat_btn);

        nameWelcome = findViewById (R.id.UserNameApper_textView);

        // information
        Bundle b = getIntent ().getExtras ();
        String editTextUsername = b.getString ("username");
        nameWelcome.setText (editTextUsername + " " + "اهلا بك يا ");
        //////////////////////


        adopte.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (selection_A.this, adopteCats_Activity.class);
                startActivity (i);
            }
        });

        lost.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (selection_A.this, LostCats_Activity.class);
                startActivity (i);
            }
        });

        charity.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (selection_A.this, HelpCats_Activity.class);
                startActivity (i);
            }
        });


    }


    ////////////////////////////////////////////menu items


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      int id = item.getItemId ();

      if (id==R.id.item2){
          Intent i = new Intent (selection_A.this,adopte_cat_add1.class);
          startActivity (i);
          return true;
      } else if (id==R.id.item3) {
          Intent i = new Intent (selection_A.this,add_lostCats.class);
          startActivity (i);
          return true;
      }
       return super.onOptionsItemSelected (item);
    }
    //////////////////////////////////////////////////////////////
}
