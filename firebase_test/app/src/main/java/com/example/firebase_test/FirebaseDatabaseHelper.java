package com.example.firebase_test;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.DefaultTaskExecutor;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBooks ;
    private List<book> books = new ArrayList<> ();

    public interface DataStatus {
        void DataIsLoaded(List<book> books, List<String> keys);
        void DataIsInserted() ;
        void DataIsDeletes() ;
        void DataIsUpdated() ;
    }
    public FirebaseDatabaseHelper() {

        mDatabase = FirebaseDatabase.getInstance ();
        mReferenceBooks = mDatabase.getReference ("books");

    }

    public void readBooks (final DataStatus dataStatus){
        mReferenceBooks.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                books.clear();
                List<String>keys = new ArrayList<> ();
                for ( DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    book book = keyNode.getValue(book.class);
                    books.add (book);


                }
                dataStatus.DataIsLoaded (books,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addBook(book book , final DataStatus dataStatus){
        String key = mReferenceBooks.push ().getKey ();
               mReferenceBooks.child(key).setValue (book)
                .addOnSuccessListener (new OnSuccessListener<Void> () {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted ();
                    }
                });

    }


        //delete item (book)
     public void deleteBook (String key , final DataStatus dataStatus){
        mReferenceBooks.child(key).setValue (null)
                .addOnSuccessListener (new OnSuccessListener<Void> () {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted ();
                    }
                });
     }
}
