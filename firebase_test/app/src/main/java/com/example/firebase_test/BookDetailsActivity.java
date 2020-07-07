package com.example.firebase_test;

                         //DELETE and BACK INFO BUTTON java :
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {

    private EditText mAuthor_editText;
    private EditText mTitle_editText;
    private EditText mISBN_editText;
    private Spinner mBook_categories_spinner;

    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String author;
    private String title;
    private String category;
    private String isbn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_book_details);

        key = getIntent ().getStringExtra ("key");
        author = getIntent ().getStringExtra ("author");
        title = getIntent ().getStringExtra ("title");
        isbn = getIntent ().getStringExtra ("isbn");
        category = getIntent ().getStringExtra ("category");

        mAuthor_editText = (EditText) findViewById (R.id.author_editText);
        mAuthor_editText.setText (author);
        mTitle_editText = (EditText) findViewById (R.id.title_editText);
        mTitle_editText.setText (title);
        mISBN_editText = (EditText) findViewById (R.id.isbn_editText);
        mISBN_editText.setText (isbn);
        mBook_categories_spinner = (Spinner) findViewById (R.id.book_categories_spinner);
        mBook_categories_spinner.setSelection (getIndex_SpinnerItem (mBook_categories_spinner, category));

        mDelete_btn = (Button) findViewById (R.id.add_btn);
        mBack_btn = (Button) findViewById (R.id.back_btn);

        mDelete_btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper ().deleteBook (key, new FirebaseDatabaseHelper.DataStatus () {
                    @Override
                    public void DataIsLoaded(List<book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsDeletes() {

                        Toast.makeText(BookDetailsActivity.this, "deleted",Toast.LENGTH_LONG).show();
                        finish ();; return;

                    }

                    @Override
                    public void DataIsUpdated() {

                    }
                });
                mBack_btn.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        finish ();return;
                    }
                });
            }
        });

    }

    private int getIndex_SpinnerItem(Spinner spinner, String item) {
        int index = 0;
        for (int i = 0; i < spinner.getChildCount (); i++) {
            if (spinner.getItemAtPosition (i).equals (item)) {
                index = i ;
            break;

        }
    }
        return index;
}
}