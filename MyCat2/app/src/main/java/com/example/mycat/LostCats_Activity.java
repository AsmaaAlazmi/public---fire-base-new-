package com.example.mycat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;     //STUFF
                              //STUFF (java)
public class LostCats_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_lost_cats_);



        Bundle b = getIntent().getExtras();

        String add_name = b.getString("data_name");
        String add_phoneNumber = b.getString("data_phone");
        String add_area = b.getString("data_area");
        String add_blockAndStreet = b.getString("data_block");
        String add_details = b.getString("data_details");
        String add_breed = b.getString("data_breed");


        ArrayList<Cats> lostCats = new ArrayList<> ();

        Cats c1 = new Cats("Vaato","حولي","3-7","212148164","عيناه زرقاء و عمره شهرين و لونه رمادي فاتح مع خطوط سوداء","amarican short hair",R.drawable.cat2);
        Cats c2 = new Cats("Sokka","ardyiah","32-7","33568410","ذكر و لديه سن مكسور و لونه بني مع خطوط سوداء ","قط بلدي",R.drawable.cat1)  ;
        Cats c3 = new Cats(add_name,add_area,add_blockAndStreet,add_phoneNumber,add_details,add_breed,R.drawable.empty);
        Cats c4 = new Cats("سكر","الفنطاس","53-9","99584702"," قطة انثى كبيره في السن فرائها لونه ابيض و عيناها لونهما عسلي", "مكس شيرازي و بلدي" ,R.drawable.cat3);


       lostCats.add(c1);
       lostCats.add(c2);
       lostCats.add(c3);
       lostCats.add (c4);

        RecyclerView rv = findViewById (R.id.RecyclerView);

        //ترتيب  ال recycel view
        rv.setHasFixedSize (true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager (this);
        rv.setLayoutManager (lm);

        adapter adapter = new adapter(lostCats,this);
        rv.setAdapter (adapter);

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
                                          Intent i = new Intent (LostCats_Activity.this,adopte_cat_add1.class);
                                          startActivity (i);
                                          return true;
                                      } else if (id==R.id.item3) {
                                          Intent i = new Intent (LostCats_Activity.this,add_lostCats.class);
                                          startActivity (i);
                                          return true;
                                      }else if (id==R.id.item1) {
                                          Intent i = new Intent (LostCats_Activity.this,selection_A.class);
                                          startActivity (i);
                                          return true;
                                      }

                                      return super.onOptionsItemSelected (item);
                                  }
                                  //////////////////////////////////////////////////////////////
}