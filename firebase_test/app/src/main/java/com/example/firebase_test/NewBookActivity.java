package com.example.firebase_test;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class NewBookActivity extends AppCompatActivity {

    private EditText mAuthor_editText;
    private EditText mTitle_editText;
    private EditText mISBN_editText;
    private Spinner mBook_Categories_Spinner;
    private Button mAdd_btn;
    private Button mBack_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_new_book);

        mAuthor_editText = (EditText) findViewById (R.id.author_editText);
        mTitle_editText = (EditText) findViewById (R.id.title_editText);
        mBook_Categories_Spinner = (Spinner) findViewById (R.id.book_categories_spinner);
        mISBN_editText = (EditText) findViewById (R.id.isbn_editText);

        mAdd_btn = (Button) findViewById (R.id.add_btn);
        mBack_btn = (Button) findViewById (R.id.back_btn);

        mAdd_btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //java //xm;
                 book  book = new book();
                 book.setAuthor (mAuthor_editText.getText ().toString ());
                 book.setTitle (mTitle_editText.getText ().toString ());
                 book.setIsbn (mISBN_editText.getText ().toString ());
                 new FirebaseDatabaseHelper ().addBook (book, new FirebaseDatabaseHelper.DataStatus () {
                     @Override
                     public void DataIsLoaded(List<com.example.firebase_test.book> books, List<String> keys) {

                     }

                     @Override
                     public void DataIsInserted() {

                         Toast.makeText(NewBookActivity.this, "your book has been inserted",Toast.LENGTH_LONG).show();

                     }

                     @Override
                     public void DataIsDeletes() {

                     }

                     @Override
                     public void DataIsUpdated() {

                     }
                 });

            }
        });
        mBack_btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish() ; return;
            }
        });

    }
}