package com.example.mycat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class LostCats_inside extends AppCompatActivity {

    private TextView breed_inside;
    private TextView name_inside;
    private TextView street_inside;
    private TextView area_inside;
    private TextView phone_inside;
    private TextView details_inside;

    //phone call
    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;


//////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_lost_cats_inside);

        breed_inside = findViewById (R.id.breed_inside);
        name_inside = findViewById (R.id.name_inside);
        street_inside = findViewById (R.id.street_inside);
        area_inside = findViewById (R.id.area_inside);
        phone_inside = findViewById (R.id.phone_inside);
        details_inside = findViewById (R.id.details_inside);

        CircleImageView image_inside = findViewById (R.id.image_inside);


        Bundle b = getIntent().getExtras();
        Cats p = (Cats) b.getSerializable ("Cats");


        name_inside.setText (p.getName ());
        breed_inside.setText (p.getBreed ()+"");
        street_inside.setText (p.getStreetAndBlock ()+"");
        area_inside.setText (p.getArea ()+"");
        phone_inside.setText (p.getNumber ()+"");
        details_inside.setText (p.getDetails ()+"");

        image_inside.setImageResource (p.getImage ());


//////////////////////////////    phone call //////////////////////////////////////////////////////

        ImageView imageCall = findViewById(R.id.call_btn);

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }
    private void makePhoneCall() {
        String number = phone_inside.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(LostCats_inside.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LostCats_inside.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(LostCats_inside.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
//////////////////////////////////////////////////////////////////////////////////////////



    }

    ///////////////// phone call 2 //////////////

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }



    ///////////////////////////////////////////////////////
}