package com.example.firebase_test;

                                          //this is BookList_Activity from the video

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        //sign-out in menu java :
        mAuth = FirebaseAuth.getInstance ();
        //////////////////////////////

        mRecyclerView = (RecyclerView) findViewById (R.id.recyclerview_books);

        new FirebaseDatabaseHelper ().readBooks (new FirebaseDatabaseHelper.DataStatus () {
            @Override
            public void DataIsLoaded(List<book> books, List<String> keys) {

                //loading bar
                findViewById (R.id.loading_books_pb).setVisibility (View.GONE);

                new RecyclerView_config ().setConfig (mRecyclerView, MainActivity.this ,books,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsDeletes() {

            }

            @Override
            public void DataIsUpdated() {

            }
        });

    }

                          //menu (add new book ) java :

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //sign in-out from menu :
        FirebaseUser user = mAuth.getCurrentUser ();
        ////////////////////////////////
        getMenuInflater ().inflate (R.menu.booklist_activity_menu,menu);
        /////sign in out :
        if( user!= null ) {
            menu.getItem (0).setVisible (true); // new book
            menu.getItem (1).setVisible (false); //sign in-register
            menu.getItem (2).setVisible (true); //log out
        }else{
            menu.getItem (0).setVisible (false); // new book
            menu.getItem (1).setVisible (true); //sign in-register
            menu.getItem (2).setVisible (false); //log out

        }
        /////////////////////////////////////^^^^

        return super.onCreateOptionsMenu (menu);
    }

    @Override

    //sign out -in
    public boolean onPrepareOptionsMenu(Menu menu) {
        FirebaseUser user = mAuth.getCurrentUser ();
        if( user!= null ) {
            menu.getItem (0).setVisible (true); // new book
            menu.getItem (1).setVisible (false); //sign in-register
            menu.getItem (2).setVisible (true); //log out
        }else{
            menu.getItem (0).setVisible (false); // new book
            menu.getItem (1).setVisible (true); //sign in-register
            menu.getItem (2).setVisible (false); //log out

        }
        return super.onPrepareOptionsMenu (menu);
        ////////////^^
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId ()){

            case R.id.new_book :
                startActivity (new Intent (this, NewBookActivity.class));
                return true;
            case R.id.sigh_in:
                startActivity (new Intent (this, SignInActivity.class));
                return true;
            case R.id.sign_out:
             mAuth.signOut ();
             invalidateOptionsMenu ();
             RecyclerView_config.logout ();
             return true;
        }
        return super.onOptionsItemSelected (item);
    }
}