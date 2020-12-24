package com.example.meghal.vsp_online;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Tariff extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private Spinner s1;
    private TextView price;
    private ImageButton bk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tariff);
        s1=(Spinner) findViewById(R.id.service);
        price=(TextView)findViewById(R.id.price);
        bk=(ImageButton) findViewById(R.id.back);
        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Tariff.this, Shop.class);
                startActivity(intent);
                finish();
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
        Toast.makeText(Tariff.this, "Please select services", Toast.LENGTH_LONG).show();

    }
}
