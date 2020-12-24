package com.example.meghal.vsp_online;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Contact_us extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner s1;
    private EditText issue;
    private ImageButton submit;
    private Button contact, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        s1 = (Spinner) findViewById(R.id.service);
        issue = (EditText) findViewById(R.id.issue);
        submit = (ImageButton) findViewById(R.id.submit);
        contact = (Button) findViewById(R.id.contact);
        back = (Button) findViewById(R.id.back);
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

        //CONTACT BUTTON
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "9769621419";
                dialContactPhone(number);

            }
        });
        //BACK BUTTON
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact_us.this, Shop.class);
                startActivity(intent);
                finish();
            }
        });

        //SUBMIT BUTTON
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= s1.getSelectedItemPosition();
                String to="contactus@vsponline.com";
                String sub= s1.getItemAtPosition(position).toString();
                String msg= issue.getText().toString();

                if (TextUtils.isEmpty(sub)) {
                    Toast.makeText(getApplicationContext(), "Enter Subject", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(msg)) {
                    Toast.makeText(getApplicationContext(), "Enter Message", Toast.LENGTH_SHORT).show();
                    return;
                }


                //Send email
                Intent email=new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT,sub);
                email.putExtra(Intent.EXTRA_TEXT,msg);
                //need this to prompts email client only  
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Choose an Email client :"));
            }
        });
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(Contact_us.this, "Please select services", Toast.LENGTH_LONG).show();
    }
}
