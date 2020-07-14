package com.example.mycat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class add_lostCats extends AppCompatActivity {

    public Button go ;
    public ImageView mChooseBtn;
    public ImageView mImageView;
    public EditText add_name;
    public EditText add_phoneNumber;
    public EditText add_area;
    public EditText add_blockAndStreet;
    public EditText add_Details;
    public EditText add_Breed;



    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_lost_cats);

        mImageView = findViewById (R.id.add_lost_image);
        mChooseBtn = findViewById (R.id.download_btn);


         add_name = findViewById (R.id.addLost_name_textView);
         add_phoneNumber = findViewById (R.id.addLost_phone_textView);
         add_area = findViewById (R.id.addLost_area_textView);
         add_blockAndStreet = findViewById (R.id.addLost_street_textView);
        go = findViewById (R.id.confirm_lostCatAdd);
        add_Breed = findViewById (R.id.addLost_breed_textView);
        add_Details = findViewById (R.id.addLost_details_textViewCV);

////////////////////////////// button invisble //////////////////////////////

        add_name.addTextChangedListener (loginTextWatcher);
        add_blockAndStreet.addTextChangedListener(loginTextWatcher);
        add_area.addTextChangedListener(loginTextWatcher);
        add_phoneNumber.addTextChangedListener(loginTextWatcher);
        add_Breed.addTextChangedListener(loginTextWatcher);
        add_Details.addTextChangedListener(loginTextWatcher);


////////////////////////////////////////////////////
        mChooseBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (checkSelfPermission (Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                        String[] permissions ={Manifest.permission.READ_EXTERNAL_STORAGE};

                        requestPermissions (permissions,PERMISSION_CODE);
                    }
                    else{
                        pickImageFromGallery();
;
                    }

                }
                    else {
                    pickImageFromGallery();

                }
            }
        });

        ///////////////////////////go

        go.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (add_lostCats.this, LostCats_Activity.class);

                Toast.makeText(add_lostCats.this, "تمت اضافة القط",Toast.LENGTH_SHORT).show();

                i.putExtra("data_name",add_name.getText().toString());
                i.putExtra("data_phone",add_phoneNumber.getText().toString());
                i.putExtra("data_area",add_area.getText().toString());
                i.putExtra("data_block",add_blockAndStreet.getText().toString());
                i.putExtra("data_details",add_Details.getText().toString());
                i.putExtra("data_breed",add_Breed.getText().toString());

                startActivity (i);
            }
        });
        ////////////////////////////////////////////
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent (Intent.ACTION_PICK);
        intent.setType ("image/*");
        startActivityForResult (intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {





        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED){

                    pickImageFromGallery ();
                }
                else {
                    Toast.makeText (this, "Permission was denied...!",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            mImageView.setImageURI (data.getData ());
        }
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
            Intent i = new Intent (add_lostCats.this,adopte_cat_add1.class);
            startActivity (i);
            return true;
        } else if (id==R.id.item3) {
            Intent i = new Intent (add_lostCats.this,add_lostCats.class);
            startActivity (i);
            return true;
        }else if (id==R.id.item1) {
            Intent i = new Intent (add_lostCats.this,selection_A.class);
            startActivity (i);
            return true;
        }
        return super.onOptionsItemSelected (item);
    }
    //////////////////////////////////////////////////////////////


    /////////////button visible

    private TextWatcher loginTextWatcher = new TextWatcher () {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = add_name.getText().toString().trim();
            String areaInput = add_area.getText().toString().trim();
            String blockInput = add_blockAndStreet.getText().toString().trim();
            String phoneInput = add_phoneNumber.getText().toString().trim();
            String detailsInput = add_Details.getText().toString().trim();
            String breedInput = add_Breed.getText().toString().trim();

            go.setEnabled(!nameInput.isEmpty() && !areaInput.isEmpty()&& !blockInput.isEmpty()&& !phoneInput.isEmpty()
                    && !breedInput.isEmpty()&& !detailsInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    //////////////////////////////////////////////////////////////////////
}