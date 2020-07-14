package com.example.mycat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class HelpCats_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_help_cats_);

        //links
        TextView link1 = (TextView) findViewById (R.id.link_1);
        link1.setMovementMethod (LinkMovementMethod.getInstance ());


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
            Intent i = new Intent (HelpCats_Activity.this,adopte_cat_add1.class);
            startActivity (i);
            return true;
        } else if (id==R.id.item3) {
            Intent i = new Intent (HelpCats_Activity.this,add_lostCats.class);
            startActivity (i);
            return true;
        }else if (id==R.id.item1) {
            Intent i = new Intent (HelpCats_Activity.this,selection_A.class);
            startActivity (i);
            return true;
        }
        return super.onOptionsItemSelected (item);
    }
    //////////////////////////////////////////////////////////////
}