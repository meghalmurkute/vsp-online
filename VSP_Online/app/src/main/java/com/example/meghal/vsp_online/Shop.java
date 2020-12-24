package com.example.meghal.vsp_online;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth fireBaseAuth;
    private FirebaseAuth auth;
    private Spinner s1;
    private EditText add, pho;
    private TextView price;
    private Button order;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User Posts");
        fireBaseAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_shop);
        s1=(Spinner) findViewById(R.id.service);
        add=(EditText)findViewById(R.id.address);
        pho=(EditText)findViewById(R.id.phone);
        price=(TextView)findViewById(R.id.price);
        order=(Button)findViewById(R.id.order);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= fireBaseAuth.getCurrentUser();
                String usr= user.getEmail().toString().trim();
                int position= s1.getSelectedItemPosition();
                String service = s1.getItemAtPosition(position).toString().trim();
                String address = add.getText().toString().trim();
                String phone = pho.getText().toString().trim();
                String pri = price.getText().toString().trim();

                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone no.!", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserInformation userInformation = new UserInformation(usr, service, address, phone, pri);
                mDatabase.push().child(user.getUid()).setValue(userInformation);
                Toast.makeText(getApplicationContext(), "Order Placed", Toast.LENGTH_SHORT).show();



            }
        });



        s1.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Android Application Developer");
        categories.add("IOS Application Developer");
        categories.add("CCTV System");
        categories.add("Logo Desiger");
        categories.add("Web Developer");
        categories.add("Insurance Agent");
        categories.add("Pest Control");
        categories.add("Electrician");
        categories.add("Plumber");
        categories.add("Carpenter");
        categories.add("Construction and Renovation");
        categories.add("House Paint");
        categories.add("Birthday Planner");
        categories.add("Wedding Planner");
        categories.add("Domestice Trip Planner");
        categories.add("International Trip Planner");
        categories.add("Interior Designer");
        categories.add("Nutritionist");
        categories.add("Photgrapher");
        categories.add("Air Conditioner");
        categories.add("Washing Machine");
        categories.add("Refrigerator");
        categories.add("Microwave");
        categories.add("Computer Hardware");
        categories.add("Computer Software");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        s1.setAdapter(dataAdapter);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shop.this, Contact_us.class);
                startActivity(intent);
                finish();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        if (item=="Android Application Developer") {
            // Handle the shop
            price.setText(R.string.a);
        } else if (item=="IOS Application Developer") {
            price.setText(R.string.b);

        } else if (item=="CCTV System") {
            price.setText(R.string.c);

        } else if (item=="Logo Desiger") {
            price.setText(R.string.d);

        } else if (item=="Web Developer") {
            price.setText(R.string.e);

        } else if (item=="Insurance Agent") {
            price.setText(R.string.f);

        }else if (item=="Pest Control") {
            price.setText(R.string.g);

        } else if (item=="Plumber") {
            price.setText(R.string.h);

        } else if (item=="Carpenter") {

            price.setText(R.string.i);
        } else if (item=="House Paint") {
            price.setText(R.string.j);

        } else if (item=="Birthday Planner") {
            price.setText(R.string.k);

        }else if (item=="Wedding Planner") {
            price.setText(R.string.l);

        } else if (item=="Domestice Trip Planner") {
            price.setText(R.string.m);

        } else if (item=="Nutritionist") {
            price.setText(R.string.n);

        } else if (item=="Interior Designer") {
            price.setText(R.string.o);

        } else if (item=="Photgrapher") {
            price.setText(R.string.p);

        }else if (item=="Air Conditioner") {
            price.setText(R.string.q);

        } else if (item=="Washing Machine") {
            price.setText(R.string.r);

        } else if (item=="Refrigerator") {
            price.setText(R.string.s);

        } else if (item=="Microwave") {
            price.setText(R.string.t);

        } else if (item=="Computer Hardware") {
            price.setText(R.string.u);

        }else if (item=="Computer Software") {
            price.setText(R.string.v);

        }

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(Shop.this, "Please select services", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Shop.this, Setting.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            // Handle the shop

        } else if (id == R.id.nav_myorder) {
            Intent intent = new Intent(Shop.this, Setting.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_tarrif) {
            Intent intent = new Intent(Shop.this, Tariff.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(Shop.this, Setting.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(Shop.this, Contact_us.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.tc){
            Intent intent = new Intent(Shop.this, TC.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
